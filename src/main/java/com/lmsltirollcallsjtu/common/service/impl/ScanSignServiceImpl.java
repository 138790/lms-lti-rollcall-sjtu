package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.base.service.ScanSignBasicService;
import com.lmsltirollcallsjtu.common.bean.bo.UserStateInfo;
import com.lmsltirollcallsjtu.common.bean.dto.SignRecordDto;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.enums.SignInStateEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.ScanSignServcie;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
    public UserStateInfo scanUpdateState(String signHistoryId, Long userCode, Date nowDate) throws BusinessException, InterruptedException {

        //对签到业务加分布式锁，防止签到人数变少
        String lockKey=prefixLockKey+signHistoryId+":"+userCode;
        RLock redisLock = redisson.getLock(lockKey);
        //加分布式锁，锁超时为36秒，每隔12秒刷新重置为36秒超时,每个线程尝试获取锁的等待时长为40秒，超过40秒仍未获取到锁，才返回false。锁的默认超时为30秒，每隔30×⅓=10秒刷新重置为30秒超时，即给锁续命。
        boolean result = redisLock.tryLock(40, 36, TimeUnit.SECONDS);
        if (result) {
            try {
                //1.查询本次点名该用户的签到状态
                UserStateInfo userStateInfo = scanSignBasicService.queryStateByRecordId(signHistoryId, userCode);
                if (userStateInfo == null || StringUtils.isEmpty(userStateInfo.getState()) || StringUtils.isEmpty(userStateInfo.getUserName())) {
                    throw BusinessException.notFoundData("点名历史" + signHistoryId);
                }
                userStateInfo.setSignHistoryId(signHistoryId);
                //2.如果用户没有正常签到，则做更新状态操作，否则跳过
                if (!userStateInfo.getState().equals(SignInStateEnum.NORMAL.getCode())) {
                    //2.1 更新该学生签到状态
                    SignRecordDto signRecordDto = SignRecordDto.builder().rollcallCode(signHistoryId)
                                                                         .state(SignInStateEnum.NORMAL.getCode())
                                                                         .userCode(userCode)
                                                                         .updatedBy(String.valueOf(userCode))
                                                                         .updatedDate(nowDate).build();
                    scanSignBasicService.scanUpdateState(signRecordDto);
                    //2.2 增加签到总数
                    scanSignBasicService.updateAttendancesCount(signHistoryId);
                }
                //3.查询当前已签到总人数
                Integer currectAttendancesCount = scanSignBasicService.queryAttendancesCount(signHistoryId);
                userStateInfo.setCurrentAttendancesCount(currectAttendancesCount);
                //4.返回用户该次点名的状态信息
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
