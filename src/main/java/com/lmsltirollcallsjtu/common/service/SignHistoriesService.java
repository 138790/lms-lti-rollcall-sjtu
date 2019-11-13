package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistoryInfo;
import com.lmsltirollcallsjtu.common.bean.bo.UserCourseInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.List;

public interface SignHistoriesService {
    List<SignHistoryInfo> findSignHistoryByRollcallId(String id) throws BusinessException;

    List<SignHistoryDto> findSignHistoryListByCourseCode(Integer courseCode) throws BusinessException;
}
