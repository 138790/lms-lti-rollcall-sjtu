package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.UpdateStateBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.dao.UpdateStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStateBasicServiceImpl implements UpdateStateBasicService {
    @Autowired
    private UpdateStateDao updateStateDao;
    @Override
    public void updateUserStateBySignRecordsDto(SignRecords signRecords) {
        updateStateDao.updateUserStateBySignRecordsDto(signRecords);
    }
}
