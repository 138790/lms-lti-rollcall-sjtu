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

//=======================================具体业务异常===================================================
    /**
     *@description {desc}数据不存在
     */
    public static BusinessException notFoundData(String desc){

        BusinessExceptionEnum tempException = BusinessExceptionEnum.NO_DATA_FOUND;
        return new BusinessException(tempException.getErrorCode(),desc+tempException.getErrorMessage());
    }

}
