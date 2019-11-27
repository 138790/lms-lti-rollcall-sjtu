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
    //将勾选的点名记录有效值变为0(无效)
    void updateIsNotValidByUsersCombineList(@Param("usersCombineList") List<UsersCombine> usersCombineList);
    //将签到明细记录有效值变为0(无效)
    void updateIsNotValidByUsersCombineLists(@Param("usersCombineLists") List<UsersCombine> usersCombineLists);
}
