package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignRecordsDao {
    //学生查询自己的签到历史
    List<SignRecordsDto> findSignHistoryByUserCode(@Param("userCode") Long userCode);
    //学生查询某一次点名的签到情况
    SignRecordsDto findSignConditionByRollcallCode(@Param("rollcallCode") String rollcallCode);
}
