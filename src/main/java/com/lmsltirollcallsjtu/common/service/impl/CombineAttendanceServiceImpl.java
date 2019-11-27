package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.CombineAttendanceBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.param.IdsParam;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CombineAttendanceService;
import com.lmsltirollcallsjtu.common.utils.CombineUtil;
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
        if (idsParam.getIds().size()==1){
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_ALLOWED_OPERATION);
        }
        //根据id查询点名记录和签到明细记录
        List<UsersCombine> usersCombineListTemp = combineAttendanceBasicService.queryUsersStatesByIds(idsParam.getIds());
//        List<SectionInfo> sectionInfos = JSON.parseArray(usersCombineListTemp.get(0).getSectionListJsonStr(), SectionInfo.class);
        List<String> sectionList = usersCombineListTemp.stream().map(item -> item.getSectionListJsonStr()).collect(Collectors.toList());
        //判断是否为同一组班级，是才能合并
        if (sectionList.stream().distinct().count()!=1){
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_ALLOWED_OPERATION);
        }
        //合并时修改点名记录和签到明细记录的有效值为0
        for (UsersCombine item:usersCombineListTemp){
            item.setIsValid(0);
            item.setUpdatedBy(idsParam.getUserCode().toString());
            item.setUpdatedDate(new Date());
        }
        combineAttendanceBasicService.updateIsNotValidByUsersCombineList(usersCombineListTemp);
        combineAttendanceBasicService.updateIsNotValidByUsersCombineLists(usersCombineListTemp);
        List<SignRecordsBo> signRecordsBos = new ArrayList<>();
        UsersCombine usersCombine;
        SignRecordsBo signRecordsBo;
        //合并后插入一条有效的点名记录
        SignHistory signHistory = SignHistory.builder().userCode( idsParam.getUserCode())
                .id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                .attendancesCount(0)
                .sectionListJsonStr( usersCombineListTemp.get(0).getSectionListJsonStr())
                .courseCode( usersCombineListTemp.get(0).getCourseCode())
                .totalStudents(usersCombineListTemp.get(0).getTotalStudents())
                .expAttendancesCount(usersCombineListTemp.get(0).getExpAttendancesCount())
                .createdBy(idsParam.getUserCode().toString())
                .build();
        //合并签到明细记录
        for (int i=0;i<usersCombineListTemp.size();i++){
            for (int j=i+1;j<usersCombineListTemp.size();j++){
                if (usersCombineListTemp.get(i).getUserName().equals(usersCombineListTemp.get(j).getUserName())){
                    List<UsersCombine> usersCombineListTemp2 = new ArrayList<>();
                    usersCombineListTemp2.add(usersCombineListTemp.get(i));
                    usersCombineListTemp2.add(usersCombineListTemp.get(j));
//                    usersCombineListTemp2.add(usersCombineListTemp.get(j+1));
                    usersCombine = CombineUtil.combineStates(usersCombineListTemp2);
                    signRecordsBo=SignRecordsBo.builder().rollcallCode(signHistory.getId())
                            .openId(null)
                            .id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                            .userCode(usersCombine.getUserCode())
                            .userName(usersCombine.getUserName())
                            .state(usersCombine.getState())
                            .sectionName(usersCombine.getSectionName())
                            .createdBy(signHistory.getCreatedBy())
                            .build();
                    signRecordsBos.add(signRecordsBo);
                }else{
                    throw BusinessException.getInstance(BusinessExceptionEnum.NOT_ALLOWED_OPERATION);
                }

            }
        }
        combineAttendanceBasicService.combineInsertSignHistoryBySignHistory(signHistory);
        combineAttendanceBasicService.combineInsertSignRecordBySignRecordsInfo(signRecordsBos);
        
    }

}
