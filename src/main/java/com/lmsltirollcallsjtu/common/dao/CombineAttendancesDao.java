package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.bo.AttendancesCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CombineAttendancesDao {
    AttendancesCount CombineAttendancesCountBySectionCodes(@Param("sectionCodes") String sectionCodes);
}
