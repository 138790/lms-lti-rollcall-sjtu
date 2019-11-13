package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;

import java.util.List;

public interface RollcallBasicService {
    void insertSignHistories(SignHistory signHistory);

    void insertStudnetsDetail(List<SignRecordsBo> signRecordsBo);
}
