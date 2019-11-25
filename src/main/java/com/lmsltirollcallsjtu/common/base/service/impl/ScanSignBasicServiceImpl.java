package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.dao.ScanSignDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanSignBasicServiceImpl implements ScanSignBasicService {
    @Autowired
    private ScanSignDao scanSignDao;

    @Override
    public String queryStateByRecordId(String id) {
        return scanSignDao.queryStateByRecordId(id);
    }

    @Override
    public void scanUpdateState(SignRecordDto signRecordDto) {
        scanSignDao.scanUpdateState(signRecordDto);
    }
}
