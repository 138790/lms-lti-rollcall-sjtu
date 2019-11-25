package com.lmsltirollcallsjtu.common.enums;

import lombok.Getter;

public enum BusinessExceptionEnum {

    NEED_LOGIN("10001","用户未登录！"),
    LTI_VERIFY_EXCEPTION("10001","LTI认证异常！"),
    NOT_FOUND_TOKEN("10001","token不存在，请重新登录！"),
    NOT_FOUND_USER("10001","用户不存在，请重新登录！"),
    DECODE_TOKEN_ERROR("10001","token解码异常！"),
    VERIFY_TOKEN_FAILURE("10001","token验证失败！"),
    NOT_PERMISSION_TO_ACCESS("10001","权限不足，无法访问！"),

    ENCRYPT_ERROR("20000","SHA加密错误！"),
    SYSTEM_IS_BUSY("20000","系统繁忙，请稍后再试！"),

    NOT_NULL("30001","不能为空！"),
    ARGS_ERROR("30002","非法参数异常！"),

    NOT_DATA_FOUND_FROM_CANVAS("40000","canvas数据不存在！"),
    MISSING_PAGING_INFO("40000","缺失分页信息！"),
    DATA_ERROR("40000","数据异常！"),

    USER_NOT_BELONG_SECTION_AND_ROLE("50000","用户没有归属班级和身份！"),
    NOT_DATA_FOUND("50000","不存在！"),
    ALREADY_EXISTS("50000","已经存在！"),
    UNPUBLISHED_TITLE("50000","题目未发布！"),
    PUBLISH_FAILURE("50000","发布失败"),
    EXISTENTIAL_HISTORICAL_ANSWER("50000","已有回答记录，无法删除！"),

    SYSTEM_ERROR("400","系统异常！"),
    ROLL_BACK("400","事务回滚异常！"),
    REDIS_ERROR("400","Redis异常！"),
    INVENTORY_SHORTAGE("400","库存不足！"),
    ;


    @Getter
    private String errorCode;
    @Getter
    private String errorMessage;

    BusinessExceptionEnum(String errorCode,String errorMessage){

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


}