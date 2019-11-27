package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignRecordsBasicService;
import com.lmsltirollcallsjtu.common.bean.param.SignRecordsOfCourseParam;
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
    public List<SignRecordsDto> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourseParam signRecordsOfCourse) {
        return signRecordsDao.findSignHistoryByUserCodeAndCourseCode(signRecordsOfCourse);
    }

}
