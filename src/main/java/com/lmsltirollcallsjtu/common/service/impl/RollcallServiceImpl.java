package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.lmsltirollcallsjtu.common.base.service.RollcallBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.RollcallHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.service.RollcallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollcallServiceImpl implements RollcallService {
    @Autowired
    private RollcallBasicService rollcallBasicService;

    @Override
    public void insertSignHistories(RollcallHistory rollcallHistory) {
        SignHistory signHistory = new SignHistory();
        String section= JSON.toJSONString(rollcallHistory.getSectionCodes());
        signHistory.setSectionCodes(section);
        signHistory.setId(rollcallHistory.getId());
        signHistory.setCourseCode(rollcallHistory.getCourseCode());
        signHistory.setUserCode(rollcallHistory.getUserCode());
        signHistory.setAttendancesCount(rollcallHistory.getAttendancesCount());
        rollcallBasicService.insertSignHistories(signHistory);
//        rollcallBasicService.insertSignHistories;
    }
}
