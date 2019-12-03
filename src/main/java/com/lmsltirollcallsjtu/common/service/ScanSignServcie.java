package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.param.UpdateSignHistoryStateParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

public interface ScanSignServcie {

    void scanUpdateState(UpdateSignHistoryStateParam updateSignHistoryStateParam) throws BusinessException;
}
