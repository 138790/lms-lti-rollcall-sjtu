package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignDetailsListParam;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignHistoryListParam;
import com.lmsltirollcallsjtu.common.dao.SignHistoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SignHistoriesBasicServiceImpl implements SignHistoriesBasicService {
    @Autowired
    private SignHistoriesDao signHistoriesDao;

    @Override
    public List<SignRecordsInfo> findSignHistoryByRollcallId(QuerySignDetailsListParam querySignDetailsListParam) {

        return signHistoriesDao.findSignHistoryByRollcallId(querySignDetailsListParam);
    }

    @Override
    public List<SignHistoryDto> findSignHistoryListByCourseCode(QuerySignHistoryListParam querySignHistoryListParam) {
        return signHistoriesDao.findSignHistoryListByCourseCode(querySignHistoryListParam);
    }

    @Override
    public void deleteSignRecord(String rollcallCode) {
        signHistoriesDao.deleteSignRecord(rollcallCode);
    }

    @Override
    public void deleteSignHistory(String id) {
        signHistoriesDao.deleteSignHistory(id);
    }


}




