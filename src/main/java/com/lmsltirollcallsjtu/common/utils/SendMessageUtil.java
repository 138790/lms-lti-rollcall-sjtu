package com.lmsltirollcallsjtu.common.utils;

import org.springframework.stereotype.Component;

@Component
public class SendMessageUtil /*implements RabbitTemplate.ConfirmCallback */{

//    private static RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    public void setRabbitTemplate(RabbitTemplate rabbitTemplate){
//        SendMessageUtil.rabbitTemplate = rabbitTemplate;
//    }
//
//    /**
//     * 向队列发送消息
//     *（注意：rabbitTemplate.convertAndSend是异步操作，引入应答机制可保证消息成功发送到消息队列中。)
//     */
//    public static void sendMsg(String messageId,String content){
//
//        //封装消息ID
//        CorrelationData correlationData = new CorrelationData(messageId);
//        //封装消息内容
//        Message message = MessageBuilder.withBody(content.getBytes())
//                                        .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                                        .setContentEncoding("utf-8")
//                                        .setMessageId(messageId)
//                                        .build();
//        //将消息内容放入CorrelationData中，以便重试发送时可以获取到该消息内容
//        correlationData.setReturnedMessage(message);
//
//        //注意rabbitMQ连接工厂需要先配置开启发布者confirms消息确认应答机制，然后rabbitTemplate实例再设置setMandatory(true)，才能实现发布者确认应答机制
//        //而且SendMessageUtil类实现了RabbitTemplate.ConfirmCallback接口的confirm()方法，利用该方法做应答处理。
//        rabbitTemplate.setMandatory(true);
//        //构建回调返回的数据。
//        rabbitTemplate.setConfirmCallback(new SendMessageUtil());
//
//        //convertAndSend(String 交换机名称, String 路由关键字, final Object 发送的消息内容, CorrelationData 消息ID)
//        List<String> list = List.of(messageId.split(":"));
//        if(!CollectionUtils.isEmpty(list) && list.size()>2){
//            String exchangeName = list.get(0);
//            String routingKey = list.get(1);
//            rabbitTemplate.convertAndSend(exchangeName, routingKey, message, correlationData);
//        }
//    }
//
//    /**
//     * 生产消息确认机制：生产者往队列服务端发送消息的时候，采用应答机制
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//
//        //获取消息ID
//        String messageId = correlationData.getId();
//        //获取消息内容
//        Message returnedMessage = correlationData.getReturnedMessage();
//        String content = returnedMessage.getBody().toString();
//
//        if(ack) {
//            System.out.println("发送消息确认成功！");
//        }else {
//            long retryCount = getRetryCount(returnedMessage);
//            //重试小于5次则重新发送，超过重试次数则放弃重试并将消息记录到日志表中。
//            if(retryCount<5L){
//                System.out.println("第"+retryCount+"次重试发送消息，原因："+cause);
//                //重新发送消息
//                sendMsg(messageId,content);
//            }else{
//                System.out.println("发送消息确认失败：" + cause);
//                System.out.println("====开始记录日志===");
//                //如果重试4次还是失败的，就将失败的消息记录到日志表中，定时任务扫描日志表或者人工干预（根据日志表信息来做补偿操作）
//
//            }
//        }
//    }
//
//    /**
//     * 获取重试次数
//     */
//    private long getRetryCount(Message message){
//
//        MessageProperties properties = message.getMessageProperties();
//        long retryCount = 0L;
//        Map<String,Object> header = properties.getHeaders();
//        if(header != null && header.containsKey("x-death")){
//            List<Map<String,Object>> deaths = (List<Map<String,Object>>)header.get("x-death");
//            if(deaths.size()>0){
//                Map<String,Object> death = deaths.get(0);
//                retryCount = (Long)death.get("count");
//            }
//        }
//        return retryCount;
//    }

}
