package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;

public interface UpdateStateService {
    public void updateUserStateBySignRecordsDto(SignRecords signRecords);
}
