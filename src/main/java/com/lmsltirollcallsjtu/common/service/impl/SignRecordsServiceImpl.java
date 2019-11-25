package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignRecordsBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.SignRecordInfo;
import com.lmsltirollcallsjtu.common.bean.param.SignRecordsOfCourseParam;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;
import com.lmsltirollcallsjtu.common.properties.CanvasFeignProperties;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.feign.CanvasFeign;
import com.lmsltirollcallsjtu.common.service.SignRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
@Service
public class SignRecordsServiceImpl implements SignRecordsService {
    @Autowired
    private SignRecordsBasicService signRecordsBasicService;
    @Autowired
    private CanvasFeign canvasFeign;
    @Autowired
    private CanvasFeignProperties canvasFeignProperties;
    @Override
    public List<SignRecordsDto> findSignHistoryByUserCodeAndCourseCode(SignRecordsOfCourseParam signRecordsOfCourseParam) throws BusinessException {
        if(signRecordsOfCourseParam.getUserCode()==null||signRecordsOfCourseParam.getUserCode()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        if(signRecordsOfCourseParam.getCourseCode()==null||signRecordsOfCourseParam.getCourseCode()<1){
            throw BusinessException.getInstance(BusinessExceptionEnum.ARGS_ERROR);
        }
        List<SignRecordsDto> signRecordsDto = signRecordsBasicService.findSignHistoryByUserCodeAndCourseCode(signRecordsOfCourseParam);

        return signRecordsDto;
    }

}
