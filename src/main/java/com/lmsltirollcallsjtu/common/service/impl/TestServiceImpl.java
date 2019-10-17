package com.lmsltirollcallsjtu.common.service.impl;

import com.lmsltirollcallsjtu.common.dao.TestDao;
import com.lmsltirollcallsjtu.common.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

}
