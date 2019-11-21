package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.security.Principal;

@Builder
@Data
public class StompAuthUser implements Principal {

    private Long userCode;

    @Override
    public String getName() {

        return String.valueOf(this.userCode);
    }


    @Tolerate
    public StompAuthUser(){

    }
}
