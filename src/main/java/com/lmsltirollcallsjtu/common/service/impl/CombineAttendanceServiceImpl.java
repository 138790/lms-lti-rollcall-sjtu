package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.CombineAttendanceBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.param.IdsParam;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CombineAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
public class CombineAttendanceServiceImpl implements CombineAttendanceService {

    @Autowired
    private CombineAttendanceBasicService combineAttendanceBasicService;

    @Override
    public void combineInsertSignHistoryBySignHistory(IdsParam idsParam) throws BusinessException {
        List<UsersCombine> usersCombineList = combineAttendanceBasicService.queryUsersStatesByIds(idsParam.getIds());
//        Map<UsersCombine, List<UsersCombine>> map = usersCombineList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.toList()));

        List<String> stateList = usersCombineList.stream().map(item -> item.getState()).collect(Collectors.toList());

        if (usersCombineList.get(0).getSectionListJsonStr().equals(usersCombineList.get(1).getSectionListJsonStr())){
            List<SignRecordsBo> signRecordsBos = new ArrayList<>();
            SignRecordsBo signRecordsBo;
            SignHistory signHistory=null;
            for (UsersCombine item:usersCombineList){
                item.setIsValid(0);
                for (String state:stateList){
                    if (item.getState().equals(state)){
                       signHistory = SignHistory.builder().userCode(item.getUserCode())
                                .id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                                .attendancesCount(0)
                                .sectionListJsonStr(item.getSectionListJsonStr())
                                .courseCode(item.getCourseCode())
                                .totalStudents(item.getTotalStudents())
                                .expAttendancesCount(item.getExpAttendancesCount())
                                .createdBy(idsParam.getUserCode().toString())
                                .build();


                        signRecordsBo=SignRecordsBo.builder().rollcallCode(signHistory.getId())
                                               .openId(null)
                                               .id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                                               .userCode(item.getUserCode())
                                               .userName(item.getUserName())
                                               .state(item.getState())
                                               .sectionName(item.getSectionName())
                                               .createdBy(signHistory.getCreatedBy())
                                               .build();
                        signRecordsBos.add(signRecordsBo);
                    }
                }
            }
            combineAttendanceBasicService.combineInsertSignHistoryBySignHistory(signHistory);
            combineAttendanceBasicService.combineInsertSignRecordBySignRecordsInfo(signRecordsBos);

        }else{
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_ALLOWED_OPERATION);
        }
        
    }



}
