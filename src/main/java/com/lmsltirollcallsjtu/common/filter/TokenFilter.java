package com.lmsltirollcallsjtu.common.filter;

import com.auth0.jwt.JWT;
import com.lmsltirollcallsjtu.common.service.SignScanQuartzJobService;
import com.lmsltirollcallsjtu.common.utils.ExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Order(1)
@WebFilter(filterName = "tokenFilter", urlPatterns = "/*", asyncSupported = true)
public class TokenFilter extends HttpFilter {

    @Autowired
    private SignScanQuartzJobService signScanQuartzJobService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        ChangedRequestWrapper changedRequestWrapper = new ChangedRequestWrapper(request);
        Map<String, String[]> parameterMap = new HashMap<>(changedRequestWrapper.getParameterMap());

        String token = request.getHeader("Authorization");
        //判断有没有传入token,如果没有,则直接通过
        if (StringUtils.isEmpty(token)) {
            changedRequestWrapper.setParameterMap(parameterMap);
        }else{
            //从token中解析出用户编号
            String userCode = JWT.decode(token).getAudience().get(0);
            ExecutionContext.setUserCode(userCode);
            parameterMap.put("userCode",userCode.split(","));
            changedRequestWrapper.setParameterMap(parameterMap);
        }
        //复写 HttpServletRequestWrapper
        filterChain.doFilter(changedRequestWrapper, response);
    }

    @Override
    public void destroy() {

        try {
            signScanQuartzJobService.shutdownJobs();
            log.error("scheduler任务调度器关闭成功！");
        } catch (SchedulerException e) {
            log.error("scheduler任务调度器关闭失败");
        }
        log.info("----------------------->过滤器被销毁");
    }
}
