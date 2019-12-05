package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistories;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;

public interface ScanSignBasicService {

    String queryStateByRecordId(String rollcallCode,Long userCode);

    void scanUpdateState(SignRecordDto signRecordDto);

    Integer selectAttendancesCount(String id);

    void updateAttendancesCount(SignHistories signHistories);
}
