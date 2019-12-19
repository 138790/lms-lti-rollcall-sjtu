package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.UserStateInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.dao.ScanSignDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanSignBasicServiceImpl implements ScanSignBasicService {
    @Autowired
    private ScanSignDao scanSignDao;

    @Override
    public UserStateInfo queryStateByRecordId(String rollcallCode, Long userCode) {
        return scanSignDao.queryStateByRecordId(rollcallCode,userCode);
    }

    @Override
    public void scanUpdateState(SignRecordDto signRecordDto) {
        scanSignDao.scanUpdateState(signRecordDto);
    }

    @Override
    public void updateAttendancesCount(String id) {
        scanSignDao.updateAttendancesCount(id);
    }

    @Override
    public Integer queryAttendancesCount(String id) {
        return scanSignDao.queryAttendancesCount(id);
    }
}
