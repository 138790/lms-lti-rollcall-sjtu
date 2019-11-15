package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecordInfo;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsOfCourse;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.List;

public interface SignRecordsService {
    List<SignRecordsDto> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourse signRecordsOfCourse) throws BusinessException;

    SignRecordsDto findSignConditionByRollcallCode(SignRecordInfo signRecordInfo) throws BusinessException;
}
