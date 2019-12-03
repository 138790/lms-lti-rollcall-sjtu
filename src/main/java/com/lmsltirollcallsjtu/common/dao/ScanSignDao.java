package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import org.apache.ibatis.annotations.Param;

public interface ScanSignDao {

    //通过主键id查询学生的状态
    String queryStateByRecordId(@Param("rollcallCode") String rollcallCode,@Param("userCode") Long userCode);
    //扫码之后签到状态改为正常
    void scanUpdateState(SignRecordDto signRecordDto);
}
