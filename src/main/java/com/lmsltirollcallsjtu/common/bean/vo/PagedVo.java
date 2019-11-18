package com.lmsltirollcallsjtu.common.bean.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;


@Data
@Builder
public class PagedVo<T> {

    private Long total;//总数
    private T dataList;//当前页数据

    public static <T> PagedVo<T> buildPagedVo(Long total,T dataList){

        return  (PagedVo<T>) builder().total(total).dataList(dataList).build();
    }
    //添加函数或者构造方法，让lombok假装它不存在（不感知）
    @Tolerate
    public PagedVo(){

    }
}
