package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecordInfo;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsOfCourse;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignRecordsDao {
    //学生查询自己某一门课的签到历史
    List<SignRecordsDto> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourse signRecordsOfCourse);
    //学生查询某一次点名的签到情况
    SignRecordsDto findSignConditionByRollcallCode(SignRecordInfo signRecordInfo);
}
