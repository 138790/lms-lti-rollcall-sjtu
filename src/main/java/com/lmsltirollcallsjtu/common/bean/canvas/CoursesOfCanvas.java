package com.lmsltirollcallsjtu.common.bean.canvas;

import com.lmsltirollcallsjtu.common.bean.bo.SectionInfo;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.List;

@Data
@Builder
public class CoursesOfCanvas {

    private Long id;//课程编号
    private String name;//课程名称
    private List<SectionInfo> sections;//对应某一课程下的一个班级

    @Tolerate
    public CoursesOfCanvas(){

    }
}
