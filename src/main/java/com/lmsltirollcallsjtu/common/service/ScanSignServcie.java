package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.UserStateInfo;
import com.lmsltirollcallsjtu.common.bean.param.UpdateSignHistoryStateParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

public interface ScanSignServcie {

    UserStateInfo scanUpdateState(String signScanToken) throws BusinessException, InterruptedException;
}
