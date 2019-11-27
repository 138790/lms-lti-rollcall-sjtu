package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.param.IdsParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;


public interface CombineAttendanceService {

    void combineInsertSignHistoryBySignHistory(IdsParam idsParam) throws BusinessException;

}
