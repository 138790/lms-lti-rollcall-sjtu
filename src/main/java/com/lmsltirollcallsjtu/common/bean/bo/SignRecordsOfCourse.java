package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SignRecordsOfCourse {
    private Long courseCode;
    private Long userCode;
    @Tolerate
    public SignRecordsOfCourse(){

    }
}
