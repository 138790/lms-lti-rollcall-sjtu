package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import org.quartz.SchedulerException;

public interface RollcallService {

    void insertSignHistories(SignHistoryParam signHistoryParam) throws BusinessException, SchedulerException;

    void backoutRollcall(String signHistoryId) throws SchedulerException, BusinessException;
}
