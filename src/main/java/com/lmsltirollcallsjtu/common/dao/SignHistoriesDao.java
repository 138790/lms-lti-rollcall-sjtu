package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.po.SignHistories;
import feign.Param;

import java.util.List;

public interface SignHistoriesDao {
    SignHistories selectSignHistoryByRollcallId(@Param("id") Long id);
}
