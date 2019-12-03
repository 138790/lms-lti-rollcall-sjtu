package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.dto.SignScanQuartzJobDto;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;

public interface SignScanQuartzJobLogDao {

    /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description 录入某次点名的定时任务日志记录
     * @param signScanQuartzJobDto
     * @return void
     */
    void saveSignScanQuartzJobLog(SignScanQuartzJobDto signScanQuartzJobDto);

     /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description 虚拟删除某次点名的定时任务记录
     * @param signHistoryId、updatedBy、updatedDate
     * @return void
     */
    void updateSignScanQuartzJobLogToInvalid(@Param("signHistoryId") String signHistoryId,
                                          @Param("updatedBy") String updatedBy,
                                          @Param("updatedDate") Date updatedDate);

     /**
     * @author wangzhijun
     * @createdDate 2019.11.27
     * @Description 查询某次点名的定时任务记录列表（仅查有效的记录）
     * @param signHistoryId
     * @return List<SignScanQuartzJobDto>
     */
    List<SignScanQuartzJobDto> querySignScanQuartzJobLogBySignHistoryId(String signHistoryId);

}
