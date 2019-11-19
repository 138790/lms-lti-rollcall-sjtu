package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.param.SignHistoryParam;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.service.RollcallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author huyong
 * @createdDate 2019.11.14
 * @Description 插入点名记录和学生信息
 * @param signHistory
 * @return ResultInfo<String>
 */
@Api(value = "/rollcall API", tags = "点名相关接口")
@RestController
@RequestMapping("/rollcall")
public class RollcallController {
    @Autowired
    private RollcallService rollcallService;
    @UserLoginToken
    @ApiOperation(value="插入点名记录并创建学生信息" ,notes = "插入点名记录并创建学生信息")

    @PostMapping("/insertSignHistories")
    public ResultInfo<String> insertSignHistories(@RequestBody @Validated SignHistoryParam signHistoryParam,@RequestParam("userCode")Long userCode){
        rollcallService.insertSignHistories(signHistoryParam);
        ResultInfo<String> resultInfo = ResultInfo.success("发起点名成功");
        return resultInfo;
    }

}
