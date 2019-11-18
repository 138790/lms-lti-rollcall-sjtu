package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistoryInfo;
import com.lmsltirollcallsjtu.common.bean.bo.UserCourseInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignHistoryListParam;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.List;

public interface SignHistoriesService {
    /**
     * @author huyong
     * @createdDate 2019.11.18
     * @Description 查询某一次点名签到历史详情（分页）
     * @param id
     * @return  List<SignHistoryDto>
     */
    List<SignHistoryInfo> findSignHistoryByRollcallId(String id) throws BusinessException;

    /**
     * @author huyong
     * @createdDate 2019.11.18
     * @Description 查询签到历史列表（分页）
     * @param querySignHistoryListParam
     * @return  PagedVo<List<SignHistoryDto>>
     */
    PagedVo<List<SignHistoryDto>> findSignHistoryListByCourseCode(QuerySignHistoryListParam querySignHistoryListParam) ;
}
