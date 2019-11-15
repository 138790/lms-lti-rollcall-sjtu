package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.List;

@Data
@Builder
public class SignHistory {
    private String id;//主键id
    private Long courseCode;//课程id
    private Long userCode;//用户id
    private Integer attendancesCount;//签到总数
    private List<Section> sectionList;//班级列表
    private String sectionListJsonStr; //班级列表JsonStr格式
    private Long totalStudents;//点名所选班级总人数
    @Tolerate
    public SignHistory(){

    }
}
