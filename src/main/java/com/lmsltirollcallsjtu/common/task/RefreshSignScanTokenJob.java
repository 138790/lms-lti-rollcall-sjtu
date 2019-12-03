package com.lmsltirollcallsjtu.common.task;

import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import com.lmsltirollcallsjtu.common.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;

@Slf4j
@PersistJobDataAfterExecution
public class RefreshSignScanTokenJob extends QuartzJobBean {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        log.info("========================signScanTokenJob Start：" + LocalDateTime.now());
        //1.获取signRecordId
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String signHistoryId = (String) jobDataMap.get("signHistoryId");

        //2.生成某次点名的签到token
        String singScanToken = TokenUtil.generateSignScanToken(signHistoryId);
        RedisUtil.putToMap("signScanTokens", signHistoryId, singScanToken);

        //3.推送消息到客户端
        if(!StringUtils.isEmpty(singScanToken)){
            simpMessageSendingOperations.convertAndSend("/topic/"+signHistoryId, singScanToken);
        }
        log.info("========================TitleOfTimeoutJob End：" + LocalDateTime.now());
    }
}
