package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserCourseInfo {
    private Integer userCode;
    private Integer courseCode;

    @Tolerate
    public UserCourseInfo(){

    }
}
