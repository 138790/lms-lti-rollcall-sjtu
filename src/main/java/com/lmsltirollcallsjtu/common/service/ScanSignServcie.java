package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.param.UpdateSignHistoryStateParam;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

public interface ScanSignServcie {

    void scanUpdateState(String signScanToken,Long userCode) throws BusinessException, InterruptedException;
}
