package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Builder
@Data
public class SectionInfoOfCanvas {

    private Long id;  //班级编号
    private String name;  //班级名称
    private List<StudentOfCanvas> students; //该班级的学生信息列表
    private Integer total_students; //该班级的学生总数
//    private Long course_id;  //课程编号

    @Tolerate
    public SectionInfoOfCanvas() {

    }
}
