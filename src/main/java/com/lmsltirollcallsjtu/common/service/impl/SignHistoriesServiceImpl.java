package com.lmsltirollcallsjtu.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lmsltirollcallsjtu.common.base.service.SignHistoriesBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.*;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDto;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignDetailsListParam;
import com.lmsltirollcallsjtu.common.bean.param.QuerySignHistoryListParam;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignHistoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;


/**
 * @author huyong
 * @date 2019-11-14
 * @Description:老师查询历史签到记录,service处理业务逻辑
 */
@Service
public class SignHistoriesServiceImpl implements SignHistoriesService {
    @Autowired
    private SignHistoriesBasicService signHistoriesBasicService;

    //老师查询某一次点名签到情况
    @Override
    public PagedVo<List<SignRecordsInfo>> findSignHistoryByRollcallId(QuerySignDetailsListParam querySignDetailsListParam) throws BusinessException {
        //1.分页查询某次签到历史详情列表
        PageHelper.startPage(querySignDetailsListParam.getPageNum(),querySignDetailsListParam.getPageSize());
        Page<SignRecordsInfo> signRecordsInfoDtoPage = (Page<SignRecordsInfo>) signHistoriesBasicService.findSignHistoryByRollcallId(querySignDetailsListParam);
//        List<SignRecordsInfo> signRecordsInfo = signHistoriesBasicService.findSignHistoryByRollcallId(querySignDetailsListParam);
        //2.封装分页信息出参
        PagedVo<List<SignRecordsInfo>> signRecordsPagedVo = PagedVo.buildPagedVo(signRecordsInfoDtoPage.getTotal(), signRecordsInfoDtoPage.getResult());
        return signRecordsPagedVo;
    }


    //根据课程编号查看签到历史(分页)
    @Override
    public PagedVo<List<SignHistoryDto>> findSignHistoryListByCourseCode(QuerySignHistoryListParam querySignHistoryListParam)  {

        //1.分页查询签到历史列表
        PageHelper.startPage(querySignHistoryListParam.getPageNum(),querySignHistoryListParam.getPageSize(),"sign_histories.created_date desc");
        Page<SignHistoryDto> signHistoryDtoPage = (Page<SignHistoryDto>) signHistoriesBasicService.findSignHistoryListByCourseCode(querySignHistoryListParam);

        //2.封装分页信息出参
        PagedVo<List<SignHistoryDto>> signHistoryPagedVo = PagedVo.buildPagedVo(signHistoryDtoPage.getTotal(), signHistoryDtoPage.getResult());

        List<SignHistoryDto> signHistoryDtoList = signHistoriesBasicService.findSignHistoryListByCourseCode(querySignHistoryListParam);
        List<SectionInfo> sectionList;
        for (SignHistoryDto item:signHistoryDtoList) {
            sectionList = JSON.parseArray(item.getSectionListJsonStr(), SectionInfo.class);
            item.setSectionList(sectionList);
        }
        signHistoryPagedVo.setDataList(signHistoryDtoList);
        return signHistoryPagedVo;
    }

}
