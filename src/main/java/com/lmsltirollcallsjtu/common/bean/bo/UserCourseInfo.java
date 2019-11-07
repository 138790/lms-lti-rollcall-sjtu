package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserCourseInfo {
    private Long userId;
    private Long courseId;
}
