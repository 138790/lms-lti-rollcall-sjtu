package com.lmsltirollcallsjtu.common.service;

import com.lmsltirollcallsjtu.common.bean.param.SignRecordsOfCourseParam;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.bean.vo.PagedVo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;

import java.util.List;

public interface SignRecordsService {

    PagedVo<List<SignRecordsDto>> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourseParam signRecordsOfCourseParam) throws BusinessException;
}
