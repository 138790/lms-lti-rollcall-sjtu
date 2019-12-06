package com.lmsltirollcallsjtu.common.bean.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lmsltirollcallsjtu.common.bean.bo.Section;
import com.lmsltirollcallsjtu.common.bean.bo.SectionInfo;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class SignHistoryDto {

    private String id;//主键id
    private List<SectionInfo> sectionList;//班级信息
//    private String sectionName;//班级名称集
//    private String sectionCodes;//班级编集（json字符串格式）
//    private List<Long> sectionCodes;//班级编号集
    private Integer isCombined;//是否为合并的记录(1:是,0；否)
    @JsonIgnore
    private String sectionListJsonStr; //班级列表JsonStr格式
    private Integer attendancesCount;//签到总人数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;//创建时间
    private Long totalStudents;//点名所选班级的总人数

  @Tolerate
  public SignHistoryDto(){

  }
}
