package com.lmsltirollcallsjtu.common.utils;

import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhijun
 * @createdDate 2019.11.06
 * @description 自定义的分页工具类
 */
@Component
public class PagingUtil<T,S> {

    private Long total = 0L;
    private Integer numberOfTotalPages;
    private Integer pageNum;
    private Integer pageSize;
    private Long startIndex;
    private Long endIndex;

    public <T> PagedVo<T> getCurrentPagedInfo(Integer pageNum, Integer pageSize, List<T> dataList) throws BusinessException {

        if(pageNum == null || pageNum<=0 || pageSize == null || pageSize<=0){
            throw BusinessException.getInstance(BusinessExceptionEnum.MISSING_PAGING_INFO);
        }

        if(dataList == null || dataList.size()<=0) {
            this.total = 0L;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            return (PagedVo<T>) PagedVo.buildPagedVo(total, new ArrayList<T>());
        }

        this.total = Long.valueOf(dataList.size());
        this.pageSize = pageSize;
        this.numberOfTotalPages = ((Double) Math.ceil((double) this.total / pageSize)).intValue();
        this.pageNum = pageNum<=this.numberOfTotalPages?pageNum:this.numberOfTotalPages;
        this.startIndex = Long.valueOf(this.pageNum==1? 0 : (this.pageNum-1)*this.pageSize);
        this.endIndex = Long.valueOf(this.pageNum==this.numberOfTotalPages? this.total : this.pageNum*this.pageSize);

        //封装当前页数据，并返回
        List<T> dataListOfCurrentPage = new ArrayList<T>();
        for(int i=startIndex.intValue(); i<endIndex.intValue(); i++){
            dataListOfCurrentPage.add(dataList.get(i));
        }
        return (PagedVo<T>) PagedVo.buildPagedVo(this.total, dataListOfCurrentPage);
    }

}
