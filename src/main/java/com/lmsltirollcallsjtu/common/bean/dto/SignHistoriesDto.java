package com.lmsltirollcallsjtu.common.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 *@author huyong
 * @createdDate 2019-10-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignHistoriesDto {

    private String id;//表记录编号
    private Long courseCode;//课程编号
    private Long userCode;//学生编号
    private String createdBy;//创建者
    private Date createdDate;//创建时间
    private String updatedBy;//修改者
    private Date updatedDate;//修改时间
    private String sectionIds;//班级编号
    private Long attendancesCount;//签到总数
}
