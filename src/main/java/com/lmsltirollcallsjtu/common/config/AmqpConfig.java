package com.lmsltirollcallsjtu.common.config;

import com.lmsltirollcallsjtu.common.enums.MessageQueueEnum;
import com.lmsltirollcallsjtu.common.properties.RabbitMQProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

/**
 * @author wangzhijun
 * @createdDate 2020.01.19
 * 注意：如果交换机、队列已首次创建过，那么之后想修改绑定，必须先删除之前创建的队列和交换机，否则会启动报错
 */
@Configuration
public class AmqpConfig {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    /**
     * 在Spring Boot中实现了RabbitMQ的自动配置，在yml配置文件中添加配置信息后会自动创建ConnectionFactory以及RabbitTemplate对应Bean。
     * 但是这里我手动重新自定义了RabbitMQ连接
     */
    @Bean
    public ConnectionFactory connectionFactory(RabbitMQProperties rabbitMQProperties) {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMQProperties.getHost(),rabbitMQProperties.getPort());
        connectionFactory.setUsername(rabbitMQProperties.getUsername());
        connectionFactory.setPassword(rabbitMQProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMQProperties.getVirtualHost());
        //显示声明进行消息的回调，即发布者开启消息应答机制。需要设置ConnectionFactory的publisherConfirms属性和RabbitTemplate的mandatory属性为true，
        //一般RabbitTemplate实例化一个对象之后再设置mandatory，这样RabbitTemplate用起来更灵活。
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        connectionFactory.setPublisherReturns(rabbitMQProperties.getPublisherReturns());

        //设置连接池大小为100
        connectionFactory.setChannelCacheSize(100);

        return connectionFactory;
    }

    /**
     * 调用RabbitTemplate中的convertAndSend方法可发送消息
     * public void convertAndSend(String exchange, String routingKey, final Object object, CorrelationData correlationData)
     * exchange:交换机名称
     * routingKey:路由关键字
     * object:发送的消息内容
     * correlationData:消息ID
     */
    //必须是prototype类型。原因在于RabbitTemplate只允许设置一个ConfirmCallback方法，虽然可以将RabbitTemplate的bean设为单例然后设置回调，
    //但是这样有个缺点是使用RabbitTemplate的地方都会执行这个回调，而其他地方一般都需要修改回调，所以将RabbitTemplate的作用域@Scope设为prototype，每次bean都是新的。
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory(rabbitMQProperties));
        //发布确认保证我们知道消息是否成功到达队列中，返回ack则代表成功，nack则代表失败。想使用这个特性，
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    /**
     *序列化为JSON格式
     */
    @Bean
    public MessageConverter messageConverter() {
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
    }

    /**
     * 创建签到的交换机
     * 交换机类型有四种，如下所示：
     * FanoutExchange:将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange:通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到精确匹配BindingKey的队列
     * TopicExchange:按照“多关键字routingkey”分发到模糊匹配“多关键字BindingKey”的队列
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(MessageQueueEnum.SIGN_IN_QUEUE.getExchangeName());
    }
    /**
     * 创建签到的死信交换机
     */
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(MessageQueueEnum.SIGN_IN_DEAD_LETTER_QUEUE.getExchangeName());
    }


    /**
     * 创建签到的队列，用于两个系统间的数据交互（例如派单队列），并路由死信交换机
     */
    @Bean
    public Queue defaultQueue() {

        //队列指定死信交换机（当发生消息被拒绝(basic.reject/basic.nack)并且requeue重回队列设置成false、消息TTL过期、队列达到最大长度这3种情况中的任意一种时便转发到绑定的死信队列）
        HashMap<String, Object> argsMap = new HashMap<>();
        argsMap.put("x-dead-letter-exchange",MessageQueueEnum.SIGN_IN_DEAD_LETTER_QUEUE.getExchangeName());
        argsMap.put("x-dead-letter-routing-key",MessageQueueEnum.SIGN_IN_DEAD_LETTER_QUEUE.getRoutingKey());
        return new Queue(MessageQueueEnum.SIGN_IN_QUEUE.getQueueName(), true,false,false,argsMap); //队列持久
    }
    /**
     *创建签到的死信队列
     */
    @Bean
    public Queue deadLetterQueue() {
        return new Queue(MessageQueueEnum.SIGN_IN_DEAD_LETTER_QUEUE.getQueueName(), true); //队列持久
    }


    /**
     *将签到队列绑定到签到交换机
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(defaultQueue()).to(defaultExchange()).with(MessageQueueEnum.SIGN_IN_QUEUE.getBindingKey());
    }
    /**
     *将死信队列绑定到死信交换机
     */
    @Bean
    public Binding bindingDeadLetter() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(MessageQueueEnum.SIGN_IN_DEAD_LETTER_QUEUE.getBindingKey());
    }

}
