package com.lmsltirollcallsjtu.common.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lmsltirollcallsjtu.common.annotations.PassToken;
import com.lmsltirollcallsjtu.common.annotations.UserLoginToken;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.properties.LTIAuthProperties;
import com.lmsltirollcallsjtu.common.properties.OurServerProperties;
import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.imsglobal.lti.launch.LtiOauthVerifier;
import org.imsglobal.lti.launch.LtiVerificationException;
import org.imsglobal.lti.launch.LtiVerificationResult;
import org.imsglobal.lti.launch.LtiVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author wangzhijun
 * @createdDate 2019-10-22
 * @description 关于token验证的自定义拦截器
 **/
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    LTIAuthProperties ltiAuthProperties;
    @Autowired
    private OurServerProperties ourServerProperties;

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 用户token验证拦截（调用Controller方法之前进行拦截）
     * @param httpServletRequest、httpServletResponse、object
     * @return boolean
     **/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws BusinessException {

        //进行LTI认证，如果认证成功，便直接通过，否则继续进行用户token验证
        LtiVerifier ltiVerifier = new LtiOauthVerifier();
        String oauthConsumerKey = httpServletRequest.getParameter("oauth_consumer_key");
        if(oauthConsumerKey != null && StringUtils.isNotBlank(oauthConsumerKey)){
            try {
                LtiVerificationResult ltiResult = ltiVerifier.verify(httpServletRequest, ltiAuthProperties.getSecret(oauthConsumerKey));
                if(ltiResult.getSuccess()) {
                    //LTI认证成功
                    return true;
                }
            } catch (LtiVerificationException e) {
                throw BusinessException.getInstance(BusinessExceptionEnum.LTI_VERIFY_EXCEPTION);
            }
        }

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        //获取token、method信息
        String token = httpServletRequest.getHeader("Authorization");
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw BusinessException.getInstance(BusinessExceptionEnum.NOT_FOUND_TOKEN);
                }
                // 获取token中的userCode登录账号
                String userCode = "";
                try {
                    userCode = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw BusinessException.getInstance(BusinessExceptionEnum.DECODE_TOKEN_ERROR);
                }
                Object userData = RedisUtil.getValueFromMap("userInfos", userCode);
                //如果用户不存在，则抛出异常
                if(userData == null){
                    throw BusinessException.getInstance(BusinessExceptionEnum.NOT_FOUND_USER);
                }

                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ourServerProperties.getSign())).build();
                try {
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e) {
                    throw BusinessException.getInstance(BusinessExceptionEnum.VERIFY_TOKEN_FAILURE);
                }
                return true;
            }
        }
//        return true;
        throw BusinessException.getInstance(BusinessExceptionEnum.NOT_PERMISSION_TO_ACCESS);
    }

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 调用Controller方法完成之后且渲染视图之前进行拦截
     * @param httpServletRequest、httpServletResponse、object、modelAndView
     * @return boolean
     **/
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws BusinessException {

    }

    /**
     * @author wangzhijun
     * @createdDate 2019-10-22
     * @description 渲染视图之后进行拦截，一般用于释放资源
     * @param httpServletRequest、httpServletResponse、object、exception
     * @return boolean
     **/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object object, Exception exception) throws BusinessException {

    }
}
