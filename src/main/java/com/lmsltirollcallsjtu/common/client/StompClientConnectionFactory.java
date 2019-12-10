package com.lmsltirollcallsjtu.common.client;

import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Profile("dev")
@Component
public class StompClientConnectionFactory {

    private String url = "http://localhost:8082/lms-lti-rollcall-sjtu-dev/stomp?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.qfd0G-elhE1aGr15LrnYlIZ_3UToaOM5HeMcXrmDGBM";
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.qfd0G-elhE1aGr15LrnYlIZ_3UToaOM5HeMcXrmDGBM";

    public ListenableFuture<StompSession> connect() throws InterruptedException, ExecutionException {

        //StompSession处理器
        StompSessionHandler stompSessionHandler = new StompClientSessionHandler();
        //Stomp消息头
        StompHeaders stompHeaders = new StompHeaders();
        stompHeaders.add("Authorization", token);
        //WebSocketHttp请求头
        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
        webSocketHttpHeaders.add("Authorization", token);

        //使用WebSocket传输
        List<Transport> transports = new ArrayList<Transport>();
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        StandardWebSocketClient standardWebSocketClient=new StandardWebSocketClient(container);
        Transport webSocketTransport = new WebSocketTransport(standardWebSocketClient);
        transports.add(webSocketTransport);
        //采用该WebSocket传输创建SockJsClient
        SockJsClient sockJsClient = new SockJsClient(transports);
        //采用该SockJsClient客户端创建WebSocketStompClient
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setReceiptTimeLimit(5000);
        stompClient.setDefaultHeartbeat(new long[] { 10000l, 10000l });
        //该WebSocketStompClient设置线程池
        ThreadPoolTaskScheduler task = new ThreadPoolTaskScheduler();
        task.initialize();
        stompClient.setTaskScheduler(task);

        return stompClient.connect(url, webSocketHttpHeaders, stompHeaders, stompSessionHandler);
    }

}