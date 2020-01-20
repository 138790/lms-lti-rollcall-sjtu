package com.lmsltirollcallsjtu.common.bean.param;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Builder
@Data
public class SignInParam {
    
    private String signHistoryId;
    private Long userCode;
    private Date signInDate;
    
    
    @Tolerate
    public SignInParam(){
        
    }
}
