package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistoryInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.bo.UserCourseInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface SignHistoriesDao {
     //老师查询一次的点名的签到情况
     List<SignHistoryInfo> findSignHistoryByRollcallId(@Param("id") String id);

     //老师查询签到历史列表
    /* @Select("select inserted_at,section_ids,attendances_count from rollcall_sign_histories where course_id=#{courseId} and user_id=#{userId}")*/
    List<SignHistoryDto> findSignHistoryListByCourseCode(@Param("courseCode") Integer courseCode);

}
