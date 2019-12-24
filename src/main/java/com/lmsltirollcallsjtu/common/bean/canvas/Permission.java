package com.lmsltirollcallsjtu.common.bean.canvas;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Permission {

    private Boolean attach;
    private Boolean update;
    private Boolean reply;
    private Boolean delete;
    @Tolerate
    public Permission(){

    }
}
