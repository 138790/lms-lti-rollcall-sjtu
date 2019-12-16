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
    private List<SectionInfo> sectionList;//班级列表
    private String sectionListJsonStr; //班级列表JsonStr格式
    private Long totalStudents;//点名所选班级总人数
    private String createdBy;//创建者
    private String updatedBy;//修改者
    private String updatedDate;//修改时间
    private Long expAttendancesCount;//预期应到总人数
    private Integer isCombined;//是否合并(1:已合并，0:未合并)
    private Integer isValid;//是否为有效记录(1:有效，0:无效)
    @Tolerate
    public SignHistory(){

    }
}
