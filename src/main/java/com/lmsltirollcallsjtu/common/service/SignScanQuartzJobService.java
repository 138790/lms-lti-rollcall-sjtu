package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.dto.SignScanQuartzJobDto;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import org.quartz.SchedulerException;
import java.util.List;

public interface SignScanQuartzJobService {

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 启动定时任务调度器
     */
    void startJobs() throws SchedulerException;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 关闭定时任务调度器
     */
    void shutdownJobs() throws SchedulerException;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 添加一个定时任务
     * @param signScanQuartzJobDto
     */
    void addSignScanJob(SignScanQuartzJobDto signScanQuartzJobDto) throws SchedulerException, BusinessException;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @description 移除一个定时任务
     * @param signRecordId
     */
    void removeSignScanJob(String signRecordId) throws BusinessException, SchedulerException;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description 查询某次点名的定时任务记录列表（仅查有效的记录）
     * @param signRecordId
     * @return List<SignScanQuartzJobDto>
     */
    List<SignScanQuartzJobDto> querySignScanQuartzJobLogBySignRecordId(String signRecordId) throws BusinessException;
}
