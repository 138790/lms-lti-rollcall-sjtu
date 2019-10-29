package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
public class UserInfo {
    private Long rollcallId;
    private Date date=new Date();
}
