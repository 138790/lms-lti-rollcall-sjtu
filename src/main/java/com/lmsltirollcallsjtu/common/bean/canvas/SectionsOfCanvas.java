package com.lmsltirollcallsjtu.common.bean.canvas;

import com.lmsltirollcallsjtu.common.bean.bo.Students;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class SectionsOfCanvas {

    private Long id;//班级id
    private Long course_id;//课程编号
    private String name;//班级名称
    private List<Students> students;//学生列表
    private Long total_students;//班级的学生总数
    private Date created_at;//创建时间

    @Tolerate
    public SectionsOfCanvas(){

    }
}
