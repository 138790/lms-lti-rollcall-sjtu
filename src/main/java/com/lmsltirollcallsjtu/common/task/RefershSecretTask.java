package com.lmsltirollcallsjtu.common.task;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.bean.bo.UserInfo;
import com.lmsltirollcallsjtu.common.bean.vo.UserInfoVo;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import com.lmsltirollcallsjtu.common.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RefershSecretTask {
//    @Autowired
//    private SimpMessageSendingOperations simpMessageSendingOperations;
//    @Scheduled(cron="0/2 * * * * ?")//每两秒执行一次
//    public void  RefershSecret() throws BusinessException {
//        UserInfoVo userInfoVo = UserInfoVo.builder().id("44adafdb68754bd7b426c1dfff4e6504").build();
//        System.out.println("===================发送消息====================");
//        String tokenJsonStr = JSON.toJSONString(TokenUtil.generateToken(userInfoVo));
//        if(tokenJsonStr!=null){
//            simpMessageSendingOperations.convertAndSend("/topic/123", tokenJsonStr);
//            RedisUtil.setString("userScan",tokenJsonStr, 60L,TimeUnit.SECONDS);
//        }
//        }

}