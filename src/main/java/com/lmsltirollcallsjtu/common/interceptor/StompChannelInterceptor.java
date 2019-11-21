package com.lmsltirollcallsjtu.common.interceptor;

import com.lmsltirollcallsjtu.common.bean.bo.StompAuthUser;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class StompChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        //1. 判断是否首次连接请求
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            String token = accessor.getFirstNativeHeader("Authorization");    //从StompHeader中取出属性
            StompAuthUser sompAuthUser = (StompAuthUser)accessor.getSessionAttributes().get("stompUser");
            if(sompAuthUser != null){
                accessor.setUser(sompAuthUser);
                return message;
            }else{
                return null;
            }
        }
        //不是首次连接，已经成功登陆
        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {

    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        return false;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        return null;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {

    }
}
