package com.lmsltirollcallsjtu.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lmsltirollcallsjtu.common.base.service.SignRecordsBasicService;
import com.lmsltirollcallsjtu.common.bean.param.SignRecordsOfCourseParam;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.SignRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SignRecordsServiceImpl implements SignRecordsService {
    @Autowired
    private SignRecordsBasicService signRecordsBasicService;

    @Override
    public PagedVo<List<SignRecordsDto>> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourseParam signRecordsOfCourseParam) throws BusinessException {

        //1.分页查询签到历史列表
        PageHelper.startPage(signRecordsOfCourseParam.getPageNum(),signRecordsOfCourseParam.getPageSize(),"r.created_date desc");

        Page<SignRecordsDto> signRecordsDtoPage =(Page<SignRecordsDto>) signRecordsBasicService.findSignHistoryByUserCodeAndCourseCode(signRecordsOfCourseParam);
        //2.封装分页信息出参
        PagedVo<List<SignRecordsDto>> signHistoryPagedVo = PagedVo.buildPagedVo(signRecordsDtoPage.getTotal(), signRecordsDtoPage.getResult());

        return signHistoryPagedVo;
    }

}
