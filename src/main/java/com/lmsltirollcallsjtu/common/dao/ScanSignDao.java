package com.lmsltirollcallsjtu.common.dao;

import com.lmsltirollcallsjtu.common.bean.dto.SignRecordsDto;

public interface ScanSignDao {
    void scanUpdateState(SignRecordsDto signRecordsDto);
}
