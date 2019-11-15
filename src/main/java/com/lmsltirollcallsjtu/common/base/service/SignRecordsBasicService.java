package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecordInfo;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsOfCourse;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;

import java.util.List;

public interface SignRecordsBasicService {
    List<SignRecordsDto> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourse signRecordsOfCourse);

    SignRecordsDto findSignConditionByRollcallCode(SignRecordInfo signRecordInfo);
}
