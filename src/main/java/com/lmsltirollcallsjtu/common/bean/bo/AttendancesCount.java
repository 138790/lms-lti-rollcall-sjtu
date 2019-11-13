package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
public class AttendancesCount {
    private List<Section> section;//班级名称
    private Integer attendancesCount;//签到总数
    private Integer totalStudents;

    @Tolerate
    public AttendancesCount(){
        
    }
}
