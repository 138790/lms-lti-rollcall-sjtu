package com.lmsltirollcallsjtu.common.enums;

import lombok.Getter;

public enum BusinessExceptionEnum {

    NEED_LOGIN("10001","用户未登录！"),
    NOT_FOUND_TOKEN("10001","token不存在，请重新登录！"),
    LTI_VERIFY_EXCEPTION("10001","LTI认证异常！"),
    DECODE_TOKEN_ERROR("10001","token解码异常！"),
    VERIFY_TOKEN_FAILURE("10001","token验证失败！"),
    NOT_FOUND_USER("10001","用户不存在，请重新登录！"),
    NOT_PERMISSION_TO_ACCESS("10001","权限不足，无法访问！"),

    SYSTEM_IS_BUSY("400","系统繁忙，请稍后再试！"),
    SYSTEM_ERROR("400","系统异常！"),
    ROLL_BACK("400","事务回滚异常！"),
    DATA_ERROR("400","数据异常！"),
    NO_DATA_FOUND("400","数据不存在！"),
    REDIS_ERROR("400","Redis异常！"),
    INVENTORY_SHORTAGE("400","库存不足！"),
    ARGS_ERROR("400","非法参数异常！");

    @Getter
    private String errorCode;
    @Getter
    private String errorMessage;

    BusinessExceptionEnum(String errorCode,String errorMessage){

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


}