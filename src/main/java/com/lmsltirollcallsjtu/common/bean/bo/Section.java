package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Section {
    private Long sectionCode;//班级编号
    private String sectionName;//班级名字
    @Tolerate
    public Section(){

    }
}
