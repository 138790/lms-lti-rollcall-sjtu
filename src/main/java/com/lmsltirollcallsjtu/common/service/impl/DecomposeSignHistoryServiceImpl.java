package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.DecomposeSignHistoryBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignHistory;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecord;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDecomposeDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.DecomposeSignHistoryService;
import com.lmsltirollcallsjtu.common.utils.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DecomposeSignHistoryServiceImpl implements DecomposeSignHistoryService {

    @Autowired
    private DecomposeSignHistoryBasicService decomposeSignHistoryBasicService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public List<SignHistoryDecomposeDto> DecomposeSignHistory(String combinedId) throws BusinessException {

        //1.将合并后的记录分解，将合并前的记录再查询出来
        List<SignHistoryDecomposeDto> signHistoryDecomposeDtos = decomposeSignHistoryBasicService.queryBeforeCombinedSignHistory(combinedId);
        if (signHistoryDecomposeDtos==null || signHistoryDecomposeDtos.size()==0){
            throw BusinessException.notFoundData("signHistoryDecomposeDtos");
        }
        String updateUserCode = ExecutionContext.getUserCode();
        //2.将拆分后的sign_histories表字段is_valid值0改为1
        for (SignHistoryDecomposeDto item:signHistoryDecomposeDtos){
            item.setCombinedId(combinedId);
            item.setIsValid(1);
            item.setUpdatedBy(updateUserCode);
            item.setUpdatedDate(new Date());
        }
        decomposeSignHistoryBasicService.updateSignHistoryIsValid(signHistoryDecomposeDtos);

        //3.将要分解的记录sign_histories表字段is_valid值1改为0

        SignHistoryDecomposeDto signHistoryDecomposeDto = SignHistoryDecomposeDto.builder().id(combinedId)
                                                                                           .isValid(0)
                                                                                           .updatedBy(updateUserCode)
                                                                                           .updatedDate(new Date()).build();
        decomposeSignHistoryBasicService.updateDecomposeSignHistoryIsValid(signHistoryDecomposeDto);
        //4.拆分后的sign_records表字段is_valid值0改为1；
        List<SignRecord> signRecords =new ArrayList<SignRecord>();
            for(SignHistoryDecomposeDto item:signHistoryDecomposeDtos){
//                item.setRollcallCode(decomposeDto.getId());
//                item.setUpdatedBy(updateUserCode);
//                item.setUpdatedDate(new Date());
//                item.setIsValid(1);
                SignRecord signRecordTemp = SignRecord.builder().isValid(1)
                                                                .rollcallCode(item.getId())
                                                                .updatedBy(updateUserCode)
                                                                .updatedDate(new Date()).build();
                signRecords.add(signRecordTemp);
            }

        decomposeSignHistoryBasicService.updateSignRecordIsValid(signRecords);

        //将要分解的记录sign_records表字段is_valid值1改为0
        List<SignRecord> signRecordList =new ArrayList<SignRecord>();
        for (SignHistoryDecomposeDto item:signHistoryDecomposeDtos ){
//            item.setRollcallCode(combinedId);
//            item.setUpdatedBy(updateUserCode);
//            item.setUpdatedDate(new Date());
//            item.setIsValid(0);
            SignRecord signRecordTemp = SignRecord.builder().isValid(0)
                                                            .rollcallCode(combinedId)
                                                            .updatedBy(updateUserCode)
                                                            .updatedDate(new Date()).build();
            signRecordList.add(signRecordTemp);
        }

        decomposeSignHistoryBasicService.updateDecomposeSignRecordIsValid(signRecordList);
        return signHistoryDecomposeDtos;
    }


}
