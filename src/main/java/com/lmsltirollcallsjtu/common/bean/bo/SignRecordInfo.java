package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class SignRecordInfo {
    private String rollcallCode;
    private Long userCode;
    @Tolerate
    public SignRecordInfo(){

    }
}