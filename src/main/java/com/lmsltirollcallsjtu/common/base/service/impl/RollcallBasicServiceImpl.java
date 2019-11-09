package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.RollcallBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.RollcallHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.dao.RollcallDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RollcallBasicServiceImpl implements RollcallBasicService {
    @Autowired
    private RollcallDao rollcallDao;

    @Override
    public void insertSignHistories(SignHistory signHistory) {
        rollcallDao.insertSignHistories(signHistory);
    }
}
