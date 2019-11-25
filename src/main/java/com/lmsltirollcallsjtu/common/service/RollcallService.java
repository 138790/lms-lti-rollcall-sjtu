package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;
import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.List;

public interface RollcallService {
    void insertSignHistories(SignHistoryParam signHistoryParam) throws BusinessException;

}
