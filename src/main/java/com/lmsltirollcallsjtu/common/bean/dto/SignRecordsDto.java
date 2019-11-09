package com.lmsltirollcallsjtu.common.bean.dto;

import com.lmsltirollcallsjtu.common.bean.bo.Students;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/*
  *@author huyong
  *@createdDate 2019-10-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignRecordsDto {

    private String id;//表记录id
    private Long userCode;//学生学号
    private String rollcallCode;//点名编号
    private Long openId;//绑定微信编号
    private String createdBy;//创建者
    private Date createdDate;//插入时间
    private String updatedBy;//修改者
    private Date updatedDate;//修改者
    private String state;//签到状态
    private String name;//班级名称
    private List<Students> students;
}
