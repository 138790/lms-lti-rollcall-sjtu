package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistories;
import com.lmsltirollcallsjtu.common.bean.bo.UserStateInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import org.apache.ibatis.annotations.Param;

public interface ScanSignDao {

    //通过主键id查询学生的状态
    UserStateInfo queryStateByRecordId(@Param("rollcallCode") String rollcallCode, @Param("userCode") Long userCode);
    //扫码之后签到状态改为正常
    void scanUpdateState(SignRecordDto signRecordDto);
    //查询此次点名的签到总数
    Integer selectAttendancesCount(@Param("id") String id);
    //签到总数不断加1
    void updateAttendancesCount(SignHistories signHistories);
}
