package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignDetailsListParam;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignHistoryListParam;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface SignHistoriesDao {

     //老师查询一次的点名的签到情况
     List<SignRecordsInfo> findSignHistoryByRollcallId(QuerySignDetailsListParam querySignDetailsListParam);

     //老师查询签到历史列表
    /* @Select("select inserted_at,section_ids,attendances_count from rollcall_sign_histories where course_id=#{courseId} and user_id=#{userId}")*/
     List<SignHistoryDto> findSignHistoryListByCourseCode(QuerySignHistoryListParam querySignHistoryListParam);

     //删除签到明细记录
     void deleteSignRecord(@Param("id") String id);

     //删除签到历史记录
     void deleteSignHistory(@Param("id") String id);
}
