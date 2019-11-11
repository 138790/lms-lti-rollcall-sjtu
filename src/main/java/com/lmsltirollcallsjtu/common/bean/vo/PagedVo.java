package com.lmsltirollcallsjtu.common.bean.vo;

import lombok.Builder;
import lombok.Data;
import java.util.List;


@Data
@Builder
public class PagedVo<T> {

    private Long total;
    private List<T> dataList;

    public static <T> PagedVo<T> buildPagedVo(Long total,List dataList){

        return builder().total(total).dataList(dataList).build();
    }
}
