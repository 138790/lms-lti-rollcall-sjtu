package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistoryInfo;
import com.lmsltirollcallsjtu.common.bean.bo.UserCourseInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;

import java.util.List;

public interface SignHistoriesBasicService {
    List<SignHistoryInfo> findSignHistoryByRollcallId(String id);

    List<SignHistoryDto> findSignHistoryListByCourseIdAndUserId(Long userCode,Long courseCode);

}