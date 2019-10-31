package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.po.SignHistories;

import java.util.List;

public interface SignHistoriesBasicService {
    SignHistories selectSignHistoryBySectionIds(List<String> sectionIds);
}
