package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecord;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDecomposeDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;

import java.util.List;

public interface DecomposeSignHistoryBasicService {

    List<SignHistoryDecomposeDto> queryBeforeCombinedSignHistory(String combinedId);

    void updateSignHistoryIsValid(List<SignHistoryDecomposeDto> signHistoryDecomposeDtos);

    void updateDecomposeSignHistoryIsValid(SignHistoryDecomposeDto signHistoryDecomposeDto);

    void updateSignRecordIsValid(List<SignRecord> signRecords);

    void updateDecomposeSignRecordIsValid(List<SignRecord> signRecords);
}
