package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;

public interface ScanSignBasicService {

    String queryStateByRecordId(String id);

    void scanUpdateState(SignRecordDto signRecordDto);
}
