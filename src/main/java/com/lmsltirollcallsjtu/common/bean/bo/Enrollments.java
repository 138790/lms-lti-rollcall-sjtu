package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Enrollments {
    private Long id;
    private Long courseId;
    @Tolerate
    public Enrollments(){

    }
}
