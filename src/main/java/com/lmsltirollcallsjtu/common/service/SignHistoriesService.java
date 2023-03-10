package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignDetailsListParam;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignHistoryListParam;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import java.util.List;

public interface SignHistoriesService {
    /**
     * @author huyong
     * @createdDate 2019.11.18
     * @Description 查询某一次点名签到历史详情（分页）
     * @param querySignDetailsListParam
     * @return  List<SignHistoryDto>
     */
    PagedVo<List<SignRecordsInfo>> findSignHistoryByRollcallId(QuerySignDetailsListParam querySignDetailsListParam) throws BusinessException;

    /**
     * @author huyong
     * @createdDate 2019.11.18
     * @Description 查询签到历史列表（分页）
     * @param querySignHistoryListParam
     * @return  PagedVo<List<SignHistoryDto>>
     */
    PagedVo<List<SignHistoryDto>> findSignHistoryListByCourseCode(QuerySignHistoryListParam querySignHistoryListParam) ;

    void deleteSignHistory(String id) throws BusinessException;

    String querySignScanTokenById(String id) throws BusinessException;
}
