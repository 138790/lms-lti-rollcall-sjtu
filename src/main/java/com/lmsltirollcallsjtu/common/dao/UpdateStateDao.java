package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import org.apache.ibatis.annotations.Param;

public interface UpdateStateDao {
    //老师修改学生签到的状态
    void updateUserStateBySignRecordsDto(SignRecordsDto signRecordsDto);

   /* //讲修改后的状态插入数据库中
    void insertUserNewStateUserId(@Param("userId") Long userId);*/
}
