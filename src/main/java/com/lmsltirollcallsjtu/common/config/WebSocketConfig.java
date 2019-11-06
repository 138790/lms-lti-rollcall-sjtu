package com.lmsltirollcallsjtu.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author huyong
 * @date 2019-10-31
 * @Description:WebSocket配置类
 */
@Configuration
/*@EnableWebSocketMessageBroker*/
public class WebSocketConfig  {

    /*@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        *//*
         * 用户可以订阅来自以"/topic"为前缀的消息，
         * 客户端只可以订阅这个前缀的主题
         *//*
        config.enableSimpleBroker("/topic");
        *//*
         * 客户端发送过来的消息，需要以"/app"为前缀，再经过Broker转发给响应的Controller,
         *//*
        config.setApplicationDestinationPrefixes("/app");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        *//*
         * 路径"/webSocketEndPoint"被注册为STOMP端点，并指定使用SockJS协议，对外暴露，客户端通过该路径接入WebSocket服务
         *//*
        registry.addEndpoint("websocket/socketServer.action").withSockJS();
    } */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
