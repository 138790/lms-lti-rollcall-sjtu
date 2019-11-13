package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Enrollments {
    private Integer id;
    private Integer courseId;
    @Tolerate
    public Enrollments(){

    }
}
