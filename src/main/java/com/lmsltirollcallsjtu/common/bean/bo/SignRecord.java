package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class SignRecord {

    private Integer isValid;//是否有效(1:有效，0:无效)
    private String rollcallCode;//点名id
    private String updatedBy;//修改者
    private Date updatedDate;//修改日期
    @Tolerate
    public SignRecord(){

    }
}
