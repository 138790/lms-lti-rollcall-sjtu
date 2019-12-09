package com.lmsltirollcallsjtu.common.task;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {
//    @Autowired
//    private SimpMessageSendingOperations simpMessageSendingOperations;
//    @Scheduled(cron = "0/2 * * * * ?")
//    public void doTask(){
//        simpMessageSendingOperations.convertAndSend("topic/123","666666");
//    }
}
