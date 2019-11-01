package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.po.SignRecords;
import com.lmsltirollcallsjtu.common.dao.ScanSignDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanSignBasicServiceImpl implements ScanSignBasicService {
    @Autowired
    private ScanSignDao scanSignDao;

    @Override
    public void insertObject(SignRecords signRecords) {
        scanSignDao.insertObject(signRecords);
    }
}
