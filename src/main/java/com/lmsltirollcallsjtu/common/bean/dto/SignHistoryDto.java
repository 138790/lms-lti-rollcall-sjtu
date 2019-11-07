package com.lmsltirollcallsjtu.common.bean.dto;


import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Data
@Component
public class SignHistoryDto {
  /*  private Long id;*/
    private String sectionName;//班级名称
    private String sectionCodesJsonStr;//班级编号集（json字符串格式）
    private List<Long> sectionCodes;//班级编号集
    private Integer attendancesCount;//签到总人数
    private Date createdDate;//创建时间
}
