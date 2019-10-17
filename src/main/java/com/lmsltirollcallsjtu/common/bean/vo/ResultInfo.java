package com.lmsltirollcallsjtu.common.bean.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultInfo<T> {

    private String resultCode;      //结果码
    private String resultMessage;   //结果描述信息
    private T data;         //数据

    public static <T> ResultInfo<T> success(T obj) {
        return (ResultInfo<T>) builder().resultCode("200").resultMessage("操作成功！").data(obj).build();
    }

    public static <T> ResultInfo<T> failure(T obj) {
        return (ResultInfo<T>) builder().resultCode("400").resultMessage("操作失败！").data(obj).build();
    }

    public static <T> ResultInfo<T> failure(String resultCode,String resultMessage) {
        return (ResultInfo<T>) builder().resultCode(resultCode).resultMessage(resultMessage).build();
    }

    public static <T> ResultInfo<T> failure(String resultCode,String resultMessage,T obj) {
        return (ResultInfo<T>) builder().resultCode(resultCode).resultMessage(resultMessage).data(obj).build();
    }

}
