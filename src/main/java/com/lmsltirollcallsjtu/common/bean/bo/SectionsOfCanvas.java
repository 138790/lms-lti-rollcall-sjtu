package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class SectionsOfCanvas {

    private Long id;//表的主键
    private Long course_id;//课程编号
    private String name;//班级名称
    private List<Students> students;//学生信息
    private Long total_students;//学生总数
    private Date created_at;

    @Tolerate
    public SectionsOfCanvas(){

    }
}
