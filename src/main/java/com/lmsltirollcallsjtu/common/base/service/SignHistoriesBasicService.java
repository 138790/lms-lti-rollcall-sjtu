package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignDetailsListParam;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignHistoryListParam;
import java.util.List;

public interface SignHistoriesBasicService {
    List<SignRecordsInfo> findSignHistoryByRollcallId(QuerySignDetailsListParam querySignDetailsListParam);

    List<SignHistoryDto> findSignHistoryListByCourseCode(QuerySignHistoryListParam querySignHistoryListParam);

}