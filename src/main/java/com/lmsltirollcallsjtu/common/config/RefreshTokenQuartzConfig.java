package com.lmsltirollcallsjtu.common.config;

import com.lmsltirollcallsjtu.common.task.RefreshTokenQuartzTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RefreshTokenQuartzConfig {

//    @Bean
//    public JobDetail refreshTokenQuartz(){
//
//        return JobBuilder
//                .newJob(RefreshTokenQuartzTask.class)
//                .withIdentity("refreshTokenQuartzTask")
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public Trigger refreshTokenTrigger(){
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("\"0/2 * * * * ?\"");
//        return TriggerBuilder
//                .newTrigger()
//                .forJob(refreshTokenQuartz())
//                .withIdentity("refreshTokenQuartzTask")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
}
