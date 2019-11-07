package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Enrollments {
    private Long id;
    private Long courseId;
}
