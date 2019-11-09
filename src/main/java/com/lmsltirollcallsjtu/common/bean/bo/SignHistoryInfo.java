package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class SignHistoryInfo {
  /*  private Long id;*/
    private String sectionCodesJsonStr;//班级编号集（json字符串格式）
    private Integer attendancesCount;//签到总人数
    private Date createdDate;//创建时间
  @Tolerate
  public SignHistoryInfo(){

  }
}
