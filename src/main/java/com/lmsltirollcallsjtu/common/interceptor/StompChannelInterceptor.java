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

//        //1.获取StompHeaderAccessor
//        StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//        if (StompCommand.CONNECT.equals(stompHeaderAccessor.getCommand())) {
//            //1.1 如果是首次连接请求
////            String token = accessor.getFirstNativeHeader("Authorization");    //从StompHeader中取出属性
//            StompAuthUser sompAuthUser = (StompAuthUser) stompHeaderAccessor.getSessionAttributes().get("stompUser");
//            if(sompAuthUser != null){
//                stompHeaderAccessor.setUser(sompAuthUser);
//                return message;
//            }else{
//                System.out.println("============================================4");
//                return null;
//            }
//        }else if (StompCommand.DISCONNECT.equals(stompHeaderAccessor.getCommand())){
//            //1.2 如果是断开连接请求
//
//        }

        //2.如果不是首次连接，即已经成功登陆，则直接通过
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
