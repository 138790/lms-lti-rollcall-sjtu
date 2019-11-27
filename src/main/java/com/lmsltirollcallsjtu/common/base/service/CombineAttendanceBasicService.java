package com.lmsltirollcallsjtu.common.base.service;

import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordsBo;
import com.lmsltirollcallsjtu.common.bean.bo.UsersCombine;
import java.util.List;

public interface CombineAttendanceBasicService {

    List<UsersCombine> queryUsersStatesByIds(List<String> ids);

    void combineInsertSignHistoryBySignHistory(SignHistory signHistory);

    void combineInsertSignRecordBySignRecordsInfo(List<SignRecordsBo> signRecordsBo);

    void updateIsNotValidByUsersCombineList(List<UsersCombine> usersCombineList);

    void updateIsNotValidByUsersCombineLists(List<UsersCombine> usersCombineLists);
}
