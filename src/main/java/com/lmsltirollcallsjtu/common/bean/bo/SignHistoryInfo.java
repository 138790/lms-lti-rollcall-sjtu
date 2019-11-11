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
    private String studentsName;//学生姓名
    private String sectionCodes;//班级编号集（json字符串格式）
    private Long attendancesCount;//签到总人数
    private Date createdDate;//创建时间
    List<UserState> userState;//学生状态
  @Tolerate
  public SignHistoryInfo(){

  }
}
