package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.bo.UserStates;
import com.lmsltirollcallsjtu.common.bean.dto.DictionaryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.dao.SignRecordsDao;

import java.util.List;

public interface UpdateStateBasicService {

    List<DictionaryDto> queryRollcallStatesByDictType(String dictType);

    void updateUserStateByUserStates(String rollcallCode,List<UserStates> userStateList);
}
