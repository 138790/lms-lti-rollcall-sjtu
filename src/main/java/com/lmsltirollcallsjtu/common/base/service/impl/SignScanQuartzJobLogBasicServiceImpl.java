package com.lmsltirollcallsjtu.common.base.service.impl;

import com.lmsltirollcallsjtu.common.base.service.SignScanQuartzJobLogBasicService;
import com.lmsltirollcallsjtu.common.bean.dto.SignScanQuartzJobDto;
import com.lmsltirollcallsjtu.common.dao.SignScanQuartzJobLogDao;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SignScanQuartzJobLogBasicServiceImpl implements SignScanQuartzJobLogBasicService {

    @Autowired
    private SignScanQuartzJobLogDao signScanQuartzJobLogDao;

    /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description 录入某次点名的定时任务日志记录
     * @param signScanQuartzJobDto
     * @return void
     */
    @Override
    public void saveSignScanQuartzJobLog(SignScanQuartzJobDto signScanQuartzJobDto) throws BusinessException {

        if(signScanQuartzJobDto == null){
            throw BusinessException.notNull("签到的定时任务日志记录入参signScanQuartzJobDto");
        }
        signScanQuartzJobLogDao.saveSignScanQuartzJobLog(signScanQuartzJobDto);
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description 虚拟删除某次点名的定时任务记录
     * @param signHistoryId
     * @return void
     */
    @Override
    public void deleteSignScanQuartzJobLog(String signHistoryId) throws BusinessException {

        if(StringUtils.isEmpty(signHistoryId)){
            throw BusinessException.notNull("signHistoryId");
        }
        signScanQuartzJobLogDao.deleteSignScanQuartzJobLog(signHistoryId);
    }

    /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description  查询某次点名的定时任务记录列表（仅查有效的记录）
     * @param signHistoryId
     * @return List<SignScanQuartzJobDto>
     */
    @Override
    public List<SignScanQuartzJobDto> querySignScanQuartzJobLogBySignHistoryId(String signHistoryId) throws BusinessException {

        if(StringUtils.isEmpty(signHistoryId)){
            throw BusinessException.notNull("signHistoryId");
        }
        List<SignScanQuartzJobDto> signScanQuartzJobDtoList = signScanQuartzJobLogDao.querySignScanQuartzJobLogBySignHistoryId(signHistoryId);
        return signScanQuartzJobDtoList;
    }
}
