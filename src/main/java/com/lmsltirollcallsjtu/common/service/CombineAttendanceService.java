package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.AttendancesCount;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;
import com.lmsltirollcallsjtu.common.bean.bo.UsersCombine;
import com.lmsltirollcallsjtu.common.bean.param.IdsParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.List;

public interface CombineAttendanceService {

    void combineInsertSignHistoryBySignHistory(IdsParam idsParam) throws BusinessException;

}
