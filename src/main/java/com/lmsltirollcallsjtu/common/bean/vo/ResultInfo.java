package com.lmsltirollcallsjtu.common.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@ApiModel
@Builder
@Data
public class ResultInfo<T> {

    @ApiModelProperty(value = "结果码", required = true)
    private String resultCode;      //结果码
    @ApiModelProperty(value = "结果描述", required = true)
    private String resultMessage;   //结果描述信息
    @ApiModelProperty(value = "数据信息", allowEmptyValue = true)
    private T body;         //数据

    //添加函数或者构造方法，让lombok假装它不存在（不感知）
    @Tolerate
    public ResultInfo() {

    }


    public static <T> ResultInfo<T> success() {
        return (ResultInfo<T>) builder().resultCode("200").resultMessage("操作成功！").build();
    }
    public static <T> ResultInfo<T> success(T obj) {
        return (ResultInfo<T>) builder().resultCode("200").resultMessage("操作成功！").body(obj).build();
    }

    public static <T> ResultInfo<T> failure(T obj) {
        return (ResultInfo<T>) builder().resultCode("400").resultMessage("操作失败！").body(obj).build();
    }

    public static <T> ResultInfo<T> failure(String resultCode,String resultMessage) {
        return (ResultInfo<T>) builder().resultCode(resultCode).resultMessage(resultMessage).build();
    }

    public static <T> ResultInfo<T> failure(String resultCode,String resultMessage,T obj) {
        return (ResultInfo<T>) builder().resultCode(resultCode).resultMessage(resultMessage).body(obj).build();
    }

}
