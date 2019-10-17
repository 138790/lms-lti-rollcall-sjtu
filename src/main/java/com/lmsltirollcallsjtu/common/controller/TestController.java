package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * @author wangzhijun
     * @createdDate 2019.10.16
     * @Description 根据id主键查询院系信息
     * @param id
     * @return ResultInfo<AccountsPo>
     */
    @GetMapping("/account/{id}")
    public ResultInfo account(@PathVariable Long id){
        return null;
    }


}
