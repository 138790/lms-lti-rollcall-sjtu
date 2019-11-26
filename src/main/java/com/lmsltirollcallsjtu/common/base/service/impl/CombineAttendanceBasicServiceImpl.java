package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.CombineAttendanceBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.AttendancesCount;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;
import com.lmsltirollcallsjtu.common.bean.bo.UsersCombine;
import com.lmsltirollcallsjtu.common.bean.param.IdsParam;
import com.lmsltirollcallsjtu.common.dao.CombineAttendancesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombineAttendanceBasicServiceImpl implements CombineAttendanceBasicService {

    @Autowired
    private CombineAttendancesDao combineAttendancesDao;

    @Override
    public List<UsersCombine> queryUsersStatesByIds(List<String> ids) {
        return combineAttendancesDao.queryUsersStatesByIds(ids);
    }

    @Override
    public void combineInsertSignHistoryBySignHistory(SignHistory signHistory) {
        combineAttendancesDao.combineInsertSignHistoryBySignHistory(signHistory);
    }

    @Override
    public void combineInsertSignRecordBySignRecordsInfo(List<SignRecordsBo> signRecordsBo) {
        combineAttendancesDao.combineInsertSignRecordBySignRecordsInfo(signRecordsBo);
    }
}
