package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.CombineAttendanceBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.AttendancesCount;
import com.lmsltirollcallsjtu.common.dao.CombineAttendancesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombineAttendanceBasicServiceImpl implements CombineAttendanceBasicService {

    @Autowired
    private CombineAttendancesDao combineAttendancesDao;

    @Override
    public AttendancesCount CombineAttendancesCountBySectionCodes(String sectionCodes) {

        return combineAttendancesDao.CombineAttendancesCountBySectionCodes(sectionCodes);

    }
}
