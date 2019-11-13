package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RollcallDao {
    void insertSignHistories(SignHistory signHistory);

    void insertStudnetsDetail(@Param("signRecordsBo") List<SignRecordsBo> signRecordsBo);

}
