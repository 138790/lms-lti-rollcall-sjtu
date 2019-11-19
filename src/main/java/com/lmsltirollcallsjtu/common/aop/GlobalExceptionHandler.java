package com.lmsltirollcallsjtu.common.aop;

import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@Slf4j
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    /**
     * @description hibernate-validator参数校验异常处理
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo ValidationExceptionHandle(MethodArgumentNotValidException methodArgumentNotValidException) {

        ResultInfo resultInfo = ResultInfo.failure("30000","入参校验异常",methodArgumentNotValidException);
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

        if( bindingResult.hasErrors() ){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuffer errorMessage = new StringBuffer(fieldErrors.size()*16);
            for(int i=0 ; i<fieldErrors.size() ; i++){
                if(i>0){
                    errorMessage.append(" || ");
                }
                FieldError fieldError = fieldErrors.get(i);
                errorMessage.append(fieldError.getDefaultMessage());
            }
            //打印错误日志
            log.error("==============发生异常：{}===============",errorMessage.toString(),methodArgumentNotValidException);
            //封装响应提示信息
            resultInfo = ResultInfo.failure("400", errorMessage.toString());
        }

        return resultInfo;
    }


    /**
     * @description 自定义BusinessException业务异常处理
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultInfo BusinessExceptionHandle(BusinessException businessException) {

        //打印错误日志
        log.error("==============发生异常：{}===============",businessException.getErrorMessage(),businessException);

        ResultInfo resultInfo = ResultInfo.failure(businessException.getErrorCode(), businessException.getErrorMessage());
        return resultInfo;
    }

    /**
     * @description Exception运行时异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo exceptionHandle(Exception exception) {

        //打印错误日志
        log.error("==============发生异常：{}===============",exception.getMessage(),exception);

        ResultInfo resultInfo = ResultInfo.failure(exception.getMessage());
        return resultInfo;
    }

}
