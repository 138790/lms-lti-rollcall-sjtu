package com.lmsltirollcallsjtu.common.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Profile("local")
@Slf4j
@Component
public class SubscribeMsgStompClient {

    @Autowired
    private StompClientConnectionFactory stompClientConnectionFactory;
    private static StompSession stompSession;// 定义全局变量，代表一个session

    @Scheduled(cron = "0/5 * * * * ?")
    public void stompClient2() {
        if(this.stompSession == null || !this.stompSession.isConnected()) {
            try {
                //当前处于断开状态,尝试连接
                log.info("当前处于断开状态,尝试连接");
                ListenableFuture<StompSession> listenableFuture = this.stompClientConnectionFactory.connect();
                this.stompSession = listenableFuture.get();
                this.stompSession.setAutoReceipt(true);
                this.stompSession.subscribe("/topic/123", new StompClientSessionHandler());
            } catch (Exception e) {
                log.error("",e);
            }
        }
    }
}
