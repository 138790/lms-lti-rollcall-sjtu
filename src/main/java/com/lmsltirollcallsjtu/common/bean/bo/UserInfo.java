package com.lmsltirollcallsjtu.common.bean.bo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/*
 *@author huyong
 *@createdDate 2019-10-31
 */
@Data
@Builder
public class UserInfo {

    private String loginName;//登录账号
    private String passwprd;//登录密码
    private String sign;   //服务器签名

    @Tolerate
    public UserInfo(){

    }
}
