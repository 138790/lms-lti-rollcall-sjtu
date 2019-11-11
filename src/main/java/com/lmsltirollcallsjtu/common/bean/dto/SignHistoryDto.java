package com.lmsltirollcallsjtu.common.bean.dto;


import com.lmsltirollcallsjtu.common.bean.bo.UserState;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class SignHistoryDto {
    private String id;//主键id
    private String sectionName;//班级名称
    private String sectionCodes;//班级编号集（json字符串格式）
//    private List<Long> sectionCodes;//班级编号集
    private Integer attendancesCount;//签到总人数
    private Date createdDate;//创建时间
    private Long totalStudents;//班级总人数
  @Tolerate
  public SignHistoryDto(){

  }
}
