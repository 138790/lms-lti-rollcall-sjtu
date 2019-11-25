package com.lmsltirollcallsjtu.common.exception;

import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import lombok.Data;

@Data
public class BusinessException extends Exception{

    private String errorMessage;
    private String errorCode;

    public BusinessException(){

    }

    public BusinessException(String errorCode, String errorMessage){

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorMessage){

        this.errorCode = "400";
        this.errorMessage = errorMessage;
    }

    public BusinessException(BusinessExceptionEnum businessExceptionEnum){

        this.errorCode = businessExceptionEnum.getErrorCode();
        this.errorMessage = businessExceptionEnum.getErrorMessage();
    }

    public static BusinessException getInstance(BusinessExceptionEnum businessExceptionEnum){

        return new BusinessException(businessExceptionEnum);
    }

    /**
     * @description 覆盖父类的异常描述信息
     * @return String
     */
    @Override
    public String getMessage() {
        return this.errorMessage;
    }

//=======================================具体业务异常===================================================
    /**
     *@description {desc}不能为空！
     */
    public static BusinessException notNull(String desc){

        BusinessExceptionEnum tempException = BusinessExceptionEnum.NOT_NULL;
        return new BusinessException(tempException.getErrorCode(),desc+tempException.getErrorMessage());
    }

    /**
     *@description {desc}不存在！
     */
    public static BusinessException notFoundData(String desc){

        BusinessExceptionEnum tempException = BusinessExceptionEnum.NOT_DATA_FOUND;
        return new BusinessException(tempException.getErrorCode(),desc+tempException.getErrorMessage());
    }

    /**
     *@description {desc}已经存在！
     */
    public static BusinessException alreadyExists(String desc){

        BusinessExceptionEnum tempException = BusinessExceptionEnum.ALREADY_EXISTS;
        return new BusinessException(tempException.getErrorCode(),desc+tempException.getErrorMessage());
    }

}
