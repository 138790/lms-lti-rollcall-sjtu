package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import org.quartz.SchedulerException;

public interface RollcallService {
    //开始点名
    String insertSignHistories(SignHistoryParam signHistoryParam) throws BusinessException, SchedulerException;

    //关闭点名
    void backoutRollcall(String signHistoryId) throws SchedulerException, BusinessException;

    //重启点名
    void restartRollcall(String signHistoryId) throws BusinessException, SchedulerException;
}
