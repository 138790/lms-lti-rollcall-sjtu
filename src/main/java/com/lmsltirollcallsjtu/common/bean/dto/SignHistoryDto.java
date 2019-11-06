package com.lmsltirollcallsjtu.common.bean.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class SignHistoryDto {
  /*  private Long id;*/
    private String sectionCodesJsonStr;//班级编号集（json字符串格式）
  // private List<Long> sectionCodes;//班级编号集
    private Integer attendancesCount;//签到总人数
    private Date createdDate;//创建时间
}
