package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecord;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDecomposeDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DecomposeSignHistoryDao {

    //将合并后的记录分解，将合并前的记录再查询出来
    List<SignHistoryDecomposeDto> queryBeforeCombinedSignHistory(@Param("combinedId") String combinedId);

    //将拆分后的sign_histories表字段is_valid值0改为1
    void updateSignHistoryIsValid(@Param("signHistoryDecomposeDtos") List<SignHistoryDecomposeDto> signHistoryDecomposeDtos);

    //将要分解的记录sign_histories表字段is_valid值1改为0
    void updateDecomposeSignHistoryIsValid(SignHistoryDecomposeDto signHistoryDecomposeDto);

    //拆分后的sign_records表字段is_valid值0改为1；
    void updateSignRecordIsValid(@Param("signRecords")List<SignRecord> signRecords);

    //将要分解的记录sign_records表字段is_valid值1改为0
    void updateDecomposeSignRecordIsValid(@Param("signRecords")List<SignRecord> signRecords);


}
