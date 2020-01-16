package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignScanQuartzJobLogBasicService;
import com.lmsltirollcallsjtu.common.bean.dto.SignScanQuartzJobDto;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignScanQuartzJobService;
import com.lmsltirollcallsjtu.common.task.RefreshSignScanTokenJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.Date;
import java.util.List;

@Service
public class SignScanQuartzJobServiceImpl implements SignScanQuartzJobService {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SignScanQuartzJobLogBasicService signScanQuartzJobLogBasicService;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 启动定时任务调度器
     */
    @Override
    public void startJobs() throws SchedulerException {
            scheduler.start();
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 关闭定时任务调度器
     */
    @Override
    public void shutdownJobs() throws SchedulerException {
        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 添加一个定时任务
     * @param signScanQuartzJobDto
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void addSignScanJob(SignScanQuartzJobDto signScanQuartzJobDto) throws SchedulerException, BusinessException {

        //1.如果signScanQuartzJobDto入参为空，则抛出异常
        if(signScanQuartzJobDto == null){
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_DATA_FOUND);
        }

        //2.录入该次点名的定时任务日志记录
        signScanQuartzJobLogBasicService.saveSignScanQuartzJobLog(signScanQuartzJobDto);

        //3.如果定时任务调度器关闭，则先开启任务调度器
        if(scheduler.isShutdown()){
            scheduler.start();
        }

        //4.job唯一标识，并生成jobDetail
        JobKey jobKey = new JobKey(signScanQuartzJobDto.getJobName(), signScanQuartzJobDto.getJobGroup());
        JobDetail jobDetail = JobBuilder.newJob(RefreshSignScanTokenJob.class).withIdentity(jobKey).usingJobData("signHistoryId",signScanQuartzJobDto.getSignHistoryId()).build();

        //5.生成该任务的触发器
        TriggerKey triggerKey = TriggerKey.triggerKey(signScanQuartzJobDto.getTriggerName(), signScanQuartzJobDto.getTriggerGroup());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                //延迟一秒首次执行
                .startAt(new Date(System.currentTimeMillis() + 1000))
                //每隔5秒重复执行一次
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever())
                .build();

        //6.执行定时job
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 移除一个定时任务
     * @param signHistoryId
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void removeSignScanJob(String signHistoryId) throws BusinessException, SchedulerException {

        //1.查询该次点名的定时任务记录
        List<SignScanQuartzJobDto> signScanQuartzJobDtoList = querySignScanQuartzJobLogBySignHistoryId(signHistoryId);

        //2.如果定时任务记录不为空，则移除该次点名的定时任务
        if(!CollectionUtils.isEmpty(signScanQuartzJobDtoList)){
            for(SignScanQuartzJobDto signScanQuartzJobDto : signScanQuartzJobDtoList){
                //2.1 如果signScanQuartzJobDto入参为空，则抛出异常
                if(signScanQuartzJobDto == null){
                    throw BusinessException.getInstance(BusinessExceptionEnum.NOT_DATA_FOUND);
                }

                //2.2 虚拟删除该次点名的定时任务记录
                signScanQuartzJobLogBasicService.updateSignScanQuartzJobLogToInvalid(signScanQuartzJobDto.getSignHistoryId(),signScanQuartzJobDto.getUpdatedBy(),signScanQuartzJobDto.getUpdatedDate());

                //2.3 停止触发器
                TriggerKey triggerKey = TriggerKey.triggerKey(signScanQuartzJobDto.getTriggerName(),signScanQuartzJobDto.getTriggerGroup());
                scheduler.pauseTrigger(triggerKey);
                //2.4 移除触发器
                scheduler.unscheduleJob(triggerKey);

                //2.5 删除定时任务
                scheduler.deleteJob(JobKey.jobKey(signScanQuartzJobDto.getJobName(),signScanQuartzJobDto.getJobGroup()));
            }
        }
    }


    /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description 查询某次点名的定时任务记录列表（仅查有效的记录）
     * @param signHistoryId
     * @return List<SignScanQuartzJobDto>
     */
    @Override
    public List<SignScanQuartzJobDto> querySignScanQuartzJobLogBySignHistoryId(String signHistoryId) throws BusinessException {

        List<SignScanQuartzJobDto> signScanQuartzJobDtoList = signScanQuartzJobLogBasicService.querySignScanQuartzJobLogBySignHistoryId(signHistoryId);
        return signScanQuartzJobDtoList;
    }
}
