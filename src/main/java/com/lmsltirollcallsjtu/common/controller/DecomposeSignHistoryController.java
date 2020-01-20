package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.bean.dto.SignHistoryDecomposeDto;
import com.lmsltirollcallsjtu.common.bean.vo.ResultInfo;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.service.DecomposeSignHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Api(value = "/decompose API", tags = "合并记录拆分相关接口")
@RestController
@RequestMapping("/decompose")
public class DecomposeSignHistoryController {

    @Autowired
    private DecomposeSignHistoryService decomposeSignHistoryService;

    @UserLoginToken
    @ApiOperation(value="合并记录拆分" ,notes = "合并记录拆分")
    @ApiImplicitParam(name = "userCode",value = "用户编号", paramType = "path", dataType = "String")
    @GetMapping("/signhistory/{combinedId}")
    public ResultInfo<List<SignHistoryDecomposeDto>> doDeDecomposeSignHistory(@PathVariable @NotBlank String combinedId) throws BusinessException {
        List<SignHistoryDecomposeDto> signHistoryDecomposeDtos = decomposeSignHistoryService.DecomposeSignHistory(combinedId);
        ResultInfo<List<SignHistoryDecomposeDto>> resultInfo = ResultInfo.success(signHistoryDecomposeDtos);
        return resultInfo;
    }
}
