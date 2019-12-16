package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.DecomposeSignHistoryBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecord;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDecomposeDto;
import com.lmsltirollcallsjtu.common.dao.DecomposeSignHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecomposeSignHistoryBasicServiceImpl implements DecomposeSignHistoryBasicService {

    @Autowired
    private DecomposeSignHistoryDao decomposeSignHistoryDao;
    @Override
    public List<SignHistoryDecomposeDto> queryBeforeCombinedSignHistory(String combinedId) {
        return decomposeSignHistoryDao.queryBeforeCombinedSignHistory(combinedId);
    }

    @Override
    public void updateSignHistoryIsValid(List<SignHistoryDecomposeDto> signHistoryDecomposeDtos) {
        decomposeSignHistoryDao.updateSignHistoryIsValid(signHistoryDecomposeDtos);
    }

    @Override
    public void updateDecomposeSignHistoryIsValid(SignHistoryDecomposeDto signHistoryDecomposeDto) {
        decomposeSignHistoryDao.updateDecomposeSignHistoryIsValid(signHistoryDecomposeDto);
    }

    @Override
    public void updateSignRecordIsValid(List<SignRecord> signRecords) {
        decomposeSignHistoryDao.updateSignRecordIsValid(signRecords);
    }

    @Override
    public void updateDecomposeSignRecordIsValid(List<SignRecord> signRecords) {
        decomposeSignHistoryDao.updateDecomposeSignRecordIsValid(signRecords);
    }
}
