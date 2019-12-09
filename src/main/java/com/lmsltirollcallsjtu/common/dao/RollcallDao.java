package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RollcallDao {
    //新增
    void insertSignHistories(SignHistory signHistory);

    void insertStudnetsDetail(@Param("signRecordsBo") List<SignRecordsBo> signRecordsBo);


}
