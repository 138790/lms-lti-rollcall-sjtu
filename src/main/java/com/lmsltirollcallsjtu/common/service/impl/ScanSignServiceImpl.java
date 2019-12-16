package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.UserStateInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.enums.SignInStateEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import com.lmsltirollcallsjtu.common.utils.ExecutionContext;
import com.lmsltirollcallsjtu.common.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ScanSignServiceImpl implements ScanSignServcie {

    @Autowired
    private ScanSignBasicService scanSignBasicService;
    @Autowired
    private Redisson redisson;
    private static String  prefixLockKey="attendancesCount:";
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public UserStateInfo scanUpdateState(String signScanToken) throws BusinessException, InterruptedException {

        //1.获取系统当前时间、用户编号
        Date nowDate = new Date();
        Long userCode = Long.valueOf(ExecutionContext.getUserCode());

        //2.验证signScanToken
        String signHistoryId = TokenUtil.verifySignScanToken(signScanToken);

        //3.对签到业务加分布式锁，防止签到人数变少
        String lockKey=prefixLockKey+signHistoryId+":"+userCode;
        RLock redisLock = redisson.getLock(lockKey);
        //加分布式锁，锁超时为36秒，每隔12秒刷新重置为36秒超时,每个线程尝试获取锁的等待时长为40秒，超过40秒仍未获取到锁，才返回false。锁的默认超时为30秒，每隔30×⅓=10秒刷新重置为30秒超时，即给锁续命。
        boolean result = redisLock.tryLock(40, 36, TimeUnit.SECONDS);
        if (result) {
            try {
                //3.1 查询本次点名该用户的签到状态
                UserStateInfo userStateInfo = scanSignBasicService.queryStateByRecordId(signHistoryId, userCode);
                if (userStateInfo == null || StringUtils.isBlank(userStateInfo.getState()) || StringUtils.isEmpty(userStateInfo.getUserName())) {
                    throw BusinessException.notFoundData("点名历史" + signHistoryId);
                }
                userStateInfo.setSignHistoryId(signHistoryId);
                //3.2 如果用户没有正常签到，则做更新状态操作，否则跳过
                if (!userStateInfo.getState().equals(SignInStateEnum.NORMAL.getCode())) {
                    SignRecordDto signRecordDto = SignRecordDto.builder().rollcallCode(signHistoryId)
                            .state(SignInStateEnum.NORMAL.getCode())
                            .userCode(userCode)
                            .updatedBy(String.valueOf(userCode))
                            .updatedDate(nowDate).build();

                    scanSignBasicService.scanUpdateState(signRecordDto);
                    //4.签到总数不断增加
                    scanSignBasicService.updateAttendancesCount(signHistoryId);
                }
                //返回用户该次点名的状态信息
                return userStateInfo;
            } finally {
                // 释放锁
                redisLock.unlock();
            }
        }else {
            throw BusinessException.getInstance(BusinessExceptionEnum.SYSTEM_IS_BUSY);
        }

    }

}
