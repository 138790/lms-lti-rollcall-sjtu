package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;

public interface ScanSignBasicService {

    String queryStateByRecordId(String rollcallCode,Long userCode);

    void scanUpdateState(SignRecordDto signRecordDto);
}
