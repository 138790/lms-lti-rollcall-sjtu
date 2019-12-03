package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.RollcallBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;
import com.lmsltirollcallsjtu.common.dao.RollcallDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollcallBasicServiceImpl implements RollcallBasicService {
    @Autowired
    private RollcallDao rollcallDao;

    @Override
    public String insertSignHistories(SignHistory signHistory) {
        rollcallDao.insertSignHistories(signHistory);
        return signHistory.getId();
    }

    @Override
    public void insertStudnetsDetail( List<SignRecordsBo> signRecordsBo) {
        rollcallDao.insertStudnetsDetail(signRecordsBo);
    }

}
