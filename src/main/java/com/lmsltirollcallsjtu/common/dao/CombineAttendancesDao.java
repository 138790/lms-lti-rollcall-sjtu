package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsInfo;
import java.util.List;

public interface CombineAttendancesDao {
    
    List<SignRecordsInfo> findSignHistoryByRollcallId(String ...ids);
    //根据查询出的数据合并新增一条有效数据
    void combineInsertSignHistoryBySignHistory(SignHistory signHistory);
    //根据查询出的数据合并新增一条有效数据
    void combineInsertSignRecordBySignRecordsInfo(List<SignRecordsBo> signRecordsBo);

}
