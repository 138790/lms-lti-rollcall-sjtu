package com.lmsltirollcallsjtu.common.task;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.bean.bo.UserInfo;
import com.lmsltirollcallsjtu.common.utils.SecretUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RefershSecretTask {
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;
    @Scheduled(cron="0/2 * * * * ?")//每两秒执行一次
    public void  RefershSecret(){
        UserInfo userInfo = UserInfo.builder().userCode(7457L).sign("123456").build();
        System.out.println("===================发送消息====================");
        String secret = SecretUtil.generateSecret(userInfo);
        simpMessageSendingOperations.convertAndSend("/topic/123", JSON.toJSONString(secret));

    }

}