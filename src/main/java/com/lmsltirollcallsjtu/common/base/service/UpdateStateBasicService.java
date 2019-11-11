package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.dao.SignRecordsDao;

public interface UpdateStateBasicService {
    void updateUserStateBySignRecordsDto(SignRecords signRecords);
}
