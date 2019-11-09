package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import org.apache.ibatis.annotations.Param;

public interface RollcallDao {
    void insertSignHistories(SignHistory signHistory);
}
