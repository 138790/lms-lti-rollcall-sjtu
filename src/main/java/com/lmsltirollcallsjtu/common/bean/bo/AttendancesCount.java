package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
public class AttendancesCount {
    private List<Section> section;//班级名称
    private Long attendancesCount;//签到总数
    private Long totalStudents;

    @Tolerate
    public AttendancesCount(){
        
    }
}