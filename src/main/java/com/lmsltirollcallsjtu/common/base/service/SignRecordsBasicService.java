package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;

import java.util.List;

public interface SignRecordsBasicService {
    List<SignRecordsDto> findSignHistoryByUserCode(Long userCode);

    SignRecordsDto findSignConditionByRollcallCode(String rollcallCode);
}
