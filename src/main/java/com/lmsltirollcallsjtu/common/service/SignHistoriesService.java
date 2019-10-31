package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.po.SignHistories;
import feign.Param;

import java.util.List;

public interface SignHistoriesService {
    SignHistories selectSignHistoryBySectionIds( List<String> sectionIds);
}
