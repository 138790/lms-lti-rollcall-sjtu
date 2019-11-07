package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Sections {
    private Long id;//表的主键
    private Long courseId;//课程编号
    private String name;//班级名称
    private Date createdAt;//创建日期
}
