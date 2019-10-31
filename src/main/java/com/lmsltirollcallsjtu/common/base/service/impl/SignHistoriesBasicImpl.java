package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.po.SignHistories;
import com.lmsltirollcallsjtu.common.dao.SignHistoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignHistoriesBasicImpl implements SignHistoriesBasicService {
    @Autowired
    private SignHistoriesDao signHistoriesDao;

    @Override
    public SignHistories selectSignHistoryBySectionIds(List<String> sectionIds) {

        return signHistoriesDao.selectSignHistoryBySectionIds(sectionIds);
    }
}
