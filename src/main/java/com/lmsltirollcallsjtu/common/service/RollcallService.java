package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.bo.RollcallHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;

import java.util.List;

public interface RollcallService {
    void insertSignHistories(RollcallHistory rollcallHistory);
}
