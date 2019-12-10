package com.lmsltirollcallsjtu.common.client;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

@Profile("dev")
@Slf4j
public class StompClientSessionHandler  extends StompSessionHandlerAdapter {

    @Autowired
    private StompClientConnectionFactory stompClientConnectionFactory;

    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {

        log.info("============连接成功================");
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {

        return byte[].class;//设置订阅到消息用字节方式接收
    }



    @Override
    public void handleFrame(StompHeaders headers, Object payload){

        Object parse = JSON.parse((byte[]) payload);
        String recvStr = JSON.toJSONString(parse);
        log.info("========================接收订阅消息：" + recvStr);
    }

    /**
     * @description 传输中报错时，尝试重新连接
     * @param stompSession
     * @param exception
     */
    @Override
    public void handleTransportError(StompSession stompSession, Throwable exception) {

        log.error("=========连接异常===========",exception);
        //super.handleTransportError(stompSession, exception);
        try {
            Thread.sleep(3000);
            ListenableFuture<StompSession> listenableFuture = stompClientConnectionFactory.connect();
            stompSession = listenableFuture.get();
            stompSession.setAutoReceipt(true);
        } catch (InterruptedException e1) {
            log.error("",e1);
        } catch (ExecutionException e2) {
            log.error("",e2);
        }
    }
}
