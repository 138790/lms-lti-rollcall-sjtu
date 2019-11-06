package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistoryInfo;
import com.lmsltirollcallsjtu.common.bean.bo.UserCourseInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.dao.SignHistoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SignHistoriesBasicImpl implements SignHistoriesBasicService {
    @Autowired
    private SignHistoriesDao signHistoriesDao;

    @Override
    public SignHistoryInfo findSignHistoryByRollcallId(String id) {

        return signHistoriesDao.findSignHistoryByRollcallId(id);
    }

    @Override
    public List<SignHistoryDto> findSignHistoryListByCourseIdAndUserId(UserCourseInfo userCourseInfo) {
        return signHistoriesDao.findSignHistoryListByCourseIdAndUserId(userCourseInfo);
    }


}




