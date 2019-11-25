package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
public class UserState {

    private String state;//学生签到状态
    private Long userCode;//用户编号
    private String sectionListJsonStr;//班级列表信息
    private List<Section> sectionList;//班级列表信息
    private Integer attendancesCount;//签到总数

    @Tolerate
    public UserState(){

    }
}
