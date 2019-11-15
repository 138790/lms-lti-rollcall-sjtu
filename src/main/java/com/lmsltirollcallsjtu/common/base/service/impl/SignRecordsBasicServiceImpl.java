package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignRecordsBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordInfo;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsOfCourse;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.dao.SignRecordsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SignRecordsBasicServiceImpl implements SignRecordsBasicService {
    @Autowired
    private SignRecordsDao signRecordsDao;
    @Override
    public List<SignRecordsDto> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourse signRecordsOfCourse) {
        return signRecordsDao.findSignHistoryByUserCodeAndCourseCode(signRecordsOfCourse);
    }

    @Override
    public SignRecordsDto findSignConditionByRollcallCode(SignRecordInfo signRecordInfo) {
        return signRecordsDao.findSignConditionByRollcallCode(signRecordInfo);
    }
}
