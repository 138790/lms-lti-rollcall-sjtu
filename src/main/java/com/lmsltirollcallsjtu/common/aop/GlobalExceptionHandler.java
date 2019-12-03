package com.lmsltirollcallsjtu.common.aop;

import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    /**
     * @author wangzhijun
     * @description hibernate-validator 方法参数校验异常（如实体类中的@Size注解配置和数据库中该字段的长度不统一等问题）
     * @param request、constraintViolationException
     * @return ResultInfo
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResultInfo handleValidationException(HttpServletRequest request, ConstraintViolationException constraintViolationException) {
        log.error("异常:" + request.getRequestURI(), constraintViolationException);
        String collect = constraintViolationException.getConstraintViolations().stream()
                .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
        //打印错误日志
        log.info("请求参数异常",collect);
        log.error("==============发生异常：{}===============",constraintViolationException.getMessage(),constraintViolationException);
        //封装响应提示信息
        ResultInfo resultInfo = ResultInfo.failure("30000", constraintViolationException.getMessage(),collect);
        return resultInfo;
    }

    /**
     * @author wangzhijun
     * @description hibernate-validator 参数绑定异常处理
     * @param request、bindException
     * @return ResultInfo
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultInfo bindException(HttpServletRequest request, BindException bindException) {
        log.error("异常:" + request.getRequestURI(), bindException);
        Map<String,String> errorMap = new HashMap<String,String>();
        if(bindException.getBindingResult() != null){
            List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();
            fieldErrors.stream().forEach(fieldError -> {
                errorMap.put("请求路径："+request.getRequestURI()+"--请求参数："+fieldError.getField(),fieldError.getDefaultMessage());
            });
        }
        //打印错误日志
        log.error("==============发生异常：{}===============","请求参数校验异常",bindException);
        //封装响应提示信息
        ResultInfo resultInfo = ResultInfo.failure("30000","请求参数校验异常",errorMap.toString());
        return resultInfo;
    }


    /**
     * @author wangzhijun
     * @description hibernate-validator 参数校验异常处理
     * @param methodArgumentNotValidException
     * @return ResultInfo
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo ValidationExceptionHandle(HttpServletRequest request, MethodArgumentNotValidException methodArgumentNotValidException) {

        ResultInfo resultInfo = ResultInfo.failure("30000","入参校验异常",methodArgumentNotValidException);
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        if( bindingResult.hasErrors() ){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String,String> errorMap = new HashMap<String,String>();
            fieldErrors.stream().forEach(fieldError -> {
                errorMap.put("请求路径："+request.getRequestURI()+"--请求参数："+fieldError.getField(),fieldError.getDefaultMessage());
            });
            //打印错误日志
            log.error("==============发生异常：{}===============","请求参数校验异常",methodArgumentNotValidException);
            //封装响应提示信息
            resultInfo = ResultInfo.failure("30000","请求参数校验异常",errorMap.toString());
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
     * @description SchedulerException运行时异常处理
     */
    @ExceptionHandler(value = SchedulerException.class)
    @ResponseBody
    public ResultInfo exceptionHandle(SchedulerException schedulerException) {

        //打印错误日志
        log.error("==============quarzt scheduler定时任务调度异常：{}===============",schedulerException.getMessage(),schedulerException);

        ResultInfo resultInfo = ResultInfo.failure(schedulerException.getMessage());
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
