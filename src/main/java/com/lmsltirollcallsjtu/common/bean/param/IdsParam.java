package com.lmsltirollcallsjtu.common.bean.param;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.List;

@Data
@Builder
public class IdsParam {

     private List<String> ids;//表主键id集合
     private Long userCode;//用户编号
    @Tolerate
    public IdsParam(){

    }
}
