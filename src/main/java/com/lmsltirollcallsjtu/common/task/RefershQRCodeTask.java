package com.lmsltirollcallsjtu.common.task;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.bean.vo.UserRecordVo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.properties.OurServerProperties;
import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import com.lmsltirollcallsjtu.common.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RefershQRCodeTask {
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;
    @Autowired
    private OurServerProperties ourServerProperties;
    @Scheduled(cron="0/2 * * * * ?")//每两秒执行一次
    public void  RefershSecret() throws BusinessException {
        UserRecordVo userRecordVo = UserRecordVo.builder().id("3e53c2a7e34445b797a13e7d7603c433")
                                                          .sign(ourServerProperties.getSign()).build();
        String token = TokenUtil.generateToken(userRecordVo);
        RedisUtil.putToMap("signScans","3e53c2a7e34445b797a13e7d7603c433",token);
        System.out.println("===================发送消息====================");
        String tokenJsonStr = JSON.toJSONString(token);
        if(tokenJsonStr!=null){
            simpMessageSendingOperations.convertAndSend("/topic/123", tokenJsonStr);
        }
        }

}