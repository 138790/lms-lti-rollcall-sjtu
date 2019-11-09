package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
public class SignHistory {
    private Long id;
    private Long courseCode;
    private Long userCode;
    private Long attendancesCount;
    private String sectionCodes;
    @Tolerate
    public SignHistory(){

    }
}
