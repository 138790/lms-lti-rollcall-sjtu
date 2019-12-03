package com.lmsltirollcallsjtu.common.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author wangzhijun
 * @createdDate 2019.11.26
 * @description quartz配置
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private SchedulerFactoryBean factory;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 注册quartz定时任务调度器Scheduler Bean
     * @return Scheduler
     */
    @Bean
    public Scheduler scheduler() throws SchedulerException{
        Scheduler scheduler = factory.getScheduler();
//        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        return scheduler;
    }

//    // 使用jobDetail包装job
//    @Bean
//    public JobDetail myJobDetail() {
//        return JobBuilder.newJob(MyJob.class).withIdentity("myJob").storeDurably().build();
//    }
//
//    // 把jobDetail注册到trigger上去
//    @Bean
//    public Trigger myJobTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(15).repeatForever();
//
//        return TriggerBuilder.newTrigger()
//                .forJob(myJobDetail())
//                .withIdentity("myJobTrigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//
//    // 使用jobDetail包装job
//    @Bean
//    public JobDetail myCronJobDetail() {
//        return JobBuilder.newJob(MyCronJob.class).withIdentity("myCronJob").storeDurably().build();
//    }
//
//    // 把jobDetail注册到Cron表达式的trigger上去
//    @Bean
//    public Trigger CronJobTrigger() {
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
//
//        return TriggerBuilder.newTrigger()
//                .forJob(myCronJobDetail())
//                .withIdentity("myCronJobTrigger")
//                .withSchedule(cronScheduleBuilder)
//                .build();
//    }
}
