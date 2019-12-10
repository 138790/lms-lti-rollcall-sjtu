package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.UserStateInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;

public interface ScanSignBasicService {

    UserStateInfo queryStateByRecordId(String rollcallCode, Long userCode);

    void scanUpdateState(SignRecordDto signRecordDto);

    void updateAttendancesCount(String id);
}
