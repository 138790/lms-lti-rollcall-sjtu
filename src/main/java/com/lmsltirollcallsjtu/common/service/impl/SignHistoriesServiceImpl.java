package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.bean.po.SignHistories;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignHistoriesServiceImpl implements SignHistoriesService {
    @Autowired
    private SignHistoriesService signHistoriesService;

    @Override
    public SignHistories selectSignHistoryBySectionIds(List<String> sectionIds) {
        return signHistoriesService.selectSignHistoryBySectionIds(sectionIds);
    }
}
