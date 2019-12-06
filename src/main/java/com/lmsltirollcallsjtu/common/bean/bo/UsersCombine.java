package com.lmsltirollcallsjtu.common.bean.bo;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class UsersCombine {

    private String id;//点名表主键
    private String rollcallCode;//明细表外键
    private Long userCode;//用户编号
    private String userName;//用户名
    private Long courseCode;//课程编号
    private String sectionListJsonStr;//班级信息列表json串
    private List<SectionInfo> sectionList;//班级信息列表
    private Long totalStudents;//点名班级的总人数
    private String state;//签到状态
    private Long expAttendancesCount;//预期应到总人数
    private Date createdDate;//创建时间
    private Date updatedDate;//修改时间
    private String createdBy;//创建者
    private String updatedBy;//修改者
    private String sectionName;//班级名称
    private Integer isValid;//点名记录有效值 是否有效(1:有效,0:无效)
//    private Integer isCombined;//此记录是否合并(1:已合并,0:未合并)
    @Tolerate
    public UsersCombine(){

    }
}
