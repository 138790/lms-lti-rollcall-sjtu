package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Builder
@Data
public class CourseInfoOfCanvas {

    private Long id;   //课程编号
    private String name;   //课程名称
    private List<TeacherOfCanvas> teachers;
    private Term term;
    private List<SectionOfCanvas> sections;
    private Long account_id;  //院系编号

    @Tolerate
    public CourseInfoOfCanvas() {

    }
}
