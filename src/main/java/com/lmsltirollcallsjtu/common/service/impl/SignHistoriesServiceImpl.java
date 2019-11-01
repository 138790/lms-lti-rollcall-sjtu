package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.po.SignHistories;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignHistoriesServiceImpl implements SignHistoriesService {
    @Autowired
    private SignHistoriesBasicService signHistoriesBasicService;

    @Override
    public SignHistories selectSignHistoryByRollcallId(Long id) {
        return signHistoriesBasicService.selectSignHistoryByRollcallId(id);
    }
}
