package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.*;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CombineAttendancesDao {

    //根据勾选的点名记录查询学生签到明细
    List<UsersCombine> queryUsersStatesByIds(@Param("ids") List<String> ids);
    //根据查询出的数据合并新增一条有效数据
    void combineInsertSignHistoryBySignHistory(SignHistory signHistory);
    //根据查询出的数据合并新增一条有效数据
    void combineInsertSignRecordBySignRecordsInfo(@Param("signRecordsBo") List<SignRecordsBo> signRecordsBo);

}
