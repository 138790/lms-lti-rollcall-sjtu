package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

public interface ScanSignServcie {

    void scanUpdateState(SignRecordDto signRecordDto) throws BusinessException;
}
