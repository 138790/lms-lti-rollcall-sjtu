package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class SignHistoryInfo {
    private String id;//
//    private Long courseCode;//课程id
//    private String studentsName;//学生姓名
    private List<Section> sectionList;//班级列表
    private String sectionListJsonStr;//班级编号集（json字符串格式）
//    private Integer attendancesCount;//签到总人数
    private Date createdDate;//创建时间
    private List<UserState> userState;//学生状态
  @Tolerate
  public SignHistoryInfo(){

  }
}
