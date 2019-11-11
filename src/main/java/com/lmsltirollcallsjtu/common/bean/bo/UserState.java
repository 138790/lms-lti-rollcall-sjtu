package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class UserState {
    private String id;
    private String state;
    @Tolerate
    public UserState(){

    }
}
