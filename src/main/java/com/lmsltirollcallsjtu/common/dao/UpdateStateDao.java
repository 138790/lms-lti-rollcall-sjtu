package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.SignRecords;
import com.lmsltirollcallsjtu.common.bean.bo.UserStates;
import com.lmsltirollcallsjtu.common.bean.dto.DictionaryDto;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpdateStateDao {

    //根据字典类型查询字典表中所有的签到状态
    List<DictionaryDto> queryRollcallStatesByDictType(@Param("dictType") String dictType);

    //老师批量更新学生的签到状态插入数据库中
    void updateUserStateByUserStates( UserStates userStates);
}
