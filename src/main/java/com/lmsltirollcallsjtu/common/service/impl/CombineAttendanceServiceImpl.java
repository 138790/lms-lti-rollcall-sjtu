package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.CombineAttendanceBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.param.IdsParam;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.enums.SignInStateEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.CombineAttendanceService;
import com.lmsltirollcallsjtu.common.utils.CombineUtil;
import com.lmsltirollcallsjtu.common.utils.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CombineAttendanceServiceImpl implements CombineAttendanceService {

    @Autowired
    private CombineAttendanceBasicService combineAttendanceBasicService;

    @Override
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void combineInsertSignHistoryBySignHistory(IdsParam idsParam) throws BusinessException {
        //判断合并的班级是否为1个
        if (idsParam.getIds().size()==1){
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_ALLOWED_OPERATION);
        }
        //2根据id查询点名记录和签到明细记录
        List<UsersCombine> usersCombineListTemp = combineAttendanceBasicService.queryUsersStatesByIds(idsParam.getIds());
        List<String> sectionList = usersCombineListTemp.stream().map(item -> item.getSectionListJsonStr()).collect(Collectors.toList());
        //3判断是否为同一组班级，是才能合并
        if (sectionList.stream().distinct().count()!=1){
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_ALLOWED_OPERATION);
        }

        String updateUserCode = ExecutionContext.getUserCode();

        UsersCombine usersCombine;
        //4合并后插入一条有效的点名记录
        SignHistory signHistory = SignHistory.builder().userCode( Long.valueOf(updateUserCode))
                                                       .id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                                                       .attendancesCount(0)
                                                       .isCombined(1)
                                                       .sectionListJsonStr( usersCombineListTemp.get(0).getSectionListJsonStr())
                                                       .courseCode( usersCombineListTemp.get(0).getCourseCode())
                                                       .totalStudents(usersCombineListTemp.get(0).getTotalStudents())
                                                       .expAttendancesCount(usersCombineListTemp.get(0).getExpAttendancesCount())
                                                       .createdBy(updateUserCode)
                                                       .updatedBy(updateUserCode).build();


        //6合并签到明细记录
        SignRecordsBo signRecordsBo;
        List<SignRecordsBo> signRecordsBos = new ArrayList<>();
        List<UsersCombine> usersCombineListTemp2;

        for (int i=0;i<usersCombineListTemp.size();i++){
            usersCombineListTemp2 = new ArrayList<>();
            for (int j=i;j<usersCombineListTemp.size();j++){
                if(usersCombineListTemp.get(i).getUserName().equals(usersCombineListTemp.get(j).getUserName())){
                    usersCombineListTemp2.add(usersCombineListTemp.get(j));
                    i=j;
                }else{
                    break;
                }
            }
            usersCombine = CombineUtil.combineStates(usersCombineListTemp2);
            signRecordsBo = SignRecordsBo.builder().rollcallCode(signHistory.getId())
                                                   .openId("null")
                                                   .id(UUID.randomUUID().toString().replaceAll("\\-", ""))
                                                   .userCode(usersCombine.getUserCode())
                                                   .userName(usersCombine.getUserName())
                                                   .state(usersCombine.getState())
                                                   .sectionName(usersCombine.getSectionName())
                                                   .createdBy(signHistory.getCreatedBy())
                                                   .updatedBy(updateUserCode).build();
            signRecordsBos.add(signRecordsBo);
        }
        //7。合并之后的签到人数为合并后签到正常的总人数
        Integer count=signHistory.getAttendancesCount();
        for (SignRecordsBo recordsBo:signRecordsBos){
            if (recordsBo.getState().equals(SignInStateEnum.NORMAL.getCode())){
                count+=1;
            }
        }
        signHistory.setAttendancesCount(count);
        combineAttendanceBasicService.combineInsertSignHistoryBySignHistory(signHistory);
        combineAttendanceBasicService.combineInsertSignRecordBySignRecordsInfo(signRecordsBos);
        //8.将合并后的记录有效改为无效记录0，记录下合并后的id
        for (UsersCombine item:usersCombineListTemp){
            item.setIsValid(0);
            item.setUpdatedBy(updateUserCode);
            item.setUpdatedDate(new Date());
            item.setCombinedId(signHistory.getId());
        }
        combineAttendanceBasicService.updateIsNotValidByUsersCombineList(usersCombineListTemp);
        combineAttendanceBasicService.updateIsNotValidByUsersCombineLists(usersCombineListTemp);
    }

}
