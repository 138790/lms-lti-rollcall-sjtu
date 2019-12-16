package com.lmsltirollcallsjtu.common.service;


import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDecomposeDto;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.List;

public interface DecomposeSignHistoryService {

    List<SignHistoryDecomposeDto> DecomposeSignHistory(String combinedId) throws BusinessException;

}
