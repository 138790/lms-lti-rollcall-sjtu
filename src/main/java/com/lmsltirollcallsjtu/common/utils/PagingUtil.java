package com.lmsltirollcallsjtu.common.utils;

import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhijun
 * @createdDate 2019.11.06
 * @description 自定义的分页工具类
 */
public class PagingUtil {

    private static Long total;
    private static Integer numberOfTotalPages;
    private static Integer pageNum;
    private static Integer pageSize;
    private static Long startIndex;
    private static Long endIndex;

    public static <T> PagedVo<List<T>> getCurrentPagedInfo(Integer pageNum, Integer pageSize, List<T> dataList) throws BusinessException {

        if(pageNum == null || pageNum<=0 || pageSize == null || pageSize<=0){
            throw BusinessException.getInstance(BusinessExceptionEnum.MISSING_PAGING_INFO);
        }

        if(dataList == null || dataList.size()<=0) {
            PagingUtil.total = 0L;
            PagingUtil.numberOfTotalPages = 0;
            PagingUtil.pageNum = pageNum;
            PagingUtil.pageSize = pageSize;
            PagingUtil.startIndex = 0L;
            PagingUtil.endIndex = 0L;
            return PagedVo.buildPagedVo(PagingUtil.total, new ArrayList<T>());
        }

        PagingUtil.total = Long.valueOf(dataList.size());
        PagingUtil.pageSize = pageSize;
        PagingUtil.numberOfTotalPages = ((Double) Math.ceil((double) PagingUtil.total / pageSize)).intValue();
        PagingUtil.pageNum = pageNum<=PagingUtil.numberOfTotalPages?pageNum:PagingUtil.numberOfTotalPages;
        PagingUtil.startIndex = Long.valueOf(PagingUtil.pageNum==1? 0 : (PagingUtil.pageNum-1)*PagingUtil.pageSize);
        PagingUtil.endIndex = Long.valueOf(PagingUtil.pageNum==PagingUtil.numberOfTotalPages? PagingUtil.total : PagingUtil.pageNum*PagingUtil.pageSize);

        //封装当前页数据，并返回
        List<T> dataListOfCurrentPage = new ArrayList<T>();
        for(int i=PagingUtil.startIndex.intValue(); i<PagingUtil.endIndex.intValue(); i++){
            dataListOfCurrentPage.add(dataList.get(i));
        }
        return PagedVo.buildPagedVo(PagingUtil.total, dataListOfCurrentPage);
    }

}
