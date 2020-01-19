package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.dto.SignScanQuartzJobDto;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.List;

public interface SignScanQuartzJobLogBasicService {

    /**
     * @author wangzhijun
     * @createdDate 2019.11.26
     * @Description 录入某次点名的定时任务日志记录
     * @param signScanQuartzJobDto
     * @return void
     */
    void saveSignScanQuartzJobLog(SignScanQuartzJobDto signScanQuartzJobDto) throws BusinessException;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description 虚拟删除某次点名的定时任务记录
     * @param signHistoryId
     * @return void
     */
    void deleteSignScanQuartzJobLog(String signHistoryId) throws BusinessException;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description  查询某次点名的定时任务记录列表（仅查有效的记录）
     * @param signHistoryId
     * @return List<SignScanQuartzJobDto>
     */
    List<SignScanQuartzJobDto> querySignScanQuartzJobLogBySignHistoryId(String signHistoryId) throws BusinessException;
}
