package com.lmsltirollcallsjtu.common.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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