package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SectionInfo {
    private Long id;//班级编号
    private String name;//班级名称

    @Tolerate
    public SectionInfo(){

    }
}
