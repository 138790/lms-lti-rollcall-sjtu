package com.lmsltirollcallsjtu.common.config;

import com.lmsltirollcallsjtu.common.interceptor.StompChannelInterceptor;
import com.lmsltirollcallsjtu.common.interceptor.StompHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author huyong
 * @date 2019-10-31
 * @Description:WebSocket配置类
 */
@Configuration
//配置WebSocket，并在WebSocket上启用STOMP，使用STOMP协议来传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private StompChannelInterceptor stompChannelInterceptor;
    @Autowired
    private StompHandshakeInterceptor stompHandshakeInterceptor;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.11
     * @description 添加一个服务端点，来接收客户端的连接。注册STOMP协议的节点(endpoint),映射指定的url
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        //注册一个STOMP的endpoint,并指定使用SockJS协议
        registry.addEndpoint("/stomp").setAllowedOrigins("*").addInterceptors(stompHandshakeInterceptor).withSockJS();
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.11
     * @description 配置客户端订阅和服务端接收消息的地址前缀
     * @param registry
     */
    @Override
    //配置消息代理(Message Broker)
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //客户端给服务端发消息的地址前缀
        registry.setApplicationDestinationPrefixes("/rollcall");

        //配置客户端订阅消息的地址前缀。广播式配置一个/topic消息代理、点对点配置一个/user消息代理
        registry.enableSimpleBroker("/topic","/user");
        //服务端通知指定客户端的前缀，可以不设置，默认为user
        registry.setUserDestinationPrefix("/user");
    }


    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        registration.interceptors(stompChannelInterceptor);
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.11
     * @description 配置传输大小限制
     * @param registration
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {

        registration.setMessageSizeLimit(500 * 1024 * 1024);
        registration.setSendBufferSizeLimit(1024 * 1024 * 1024);
        registration.setSendTimeLimit(200000);
    }


}
