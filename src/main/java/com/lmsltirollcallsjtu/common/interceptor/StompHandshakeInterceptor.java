package com.lmsltirollcallsjtu.common.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.lmsltirollcallsjtu.common.bean.bo.StompAuthUser;
import com.lmsltirollcallsjtu.common.enums.BusinessExceptionEnum;
import com.lmsltirollcallsjtu.common.exception.BusinessException;
import com.lmsltirollcallsjtu.common.properties.OurServerProperties;
import com.lmsltirollcallsjtu.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
public class StompHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private OurServerProperties ourServerProperties;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        //1.获取token，并判断有没有传入token，如果没有则不允许通过
        String token = getToken(request);
        if (StringUtils.isBlank(token)) {
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_FOUND_TOKEN);
        }

        //2.验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ourServerProperties.getSign())).build();
        try {
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e) {
            throw BusinessException.getInstance(BusinessExceptionEnum.VERIFY_TOKEN_FAILURE);
        }

        //3.从token中解析出用户编号
        String userCode = JWT.decode(token).getAudience().get(0);

        //4.验证是否已登录，如果用户不存在，则抛出异常
        Object userData = RedisUtil.getValueFromMap("userInfos", userCode);
        if(userData == null){
            throw BusinessException.getInstance(BusinessExceptionEnum.NOT_FOUND_USER);
        }

        //5.sessionAttributes中添加userCode属性
        StompAuthUser sompAuthUser = StompAuthUser.builder().userCode(Long.parseLong(userCode)).build();
        attributes.put("stompUser",sompAuthUser);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }


    //从请求头中获取token
    private String getToken(ServerHttpRequest request) {

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            String token = serverRequest.getServletRequest().getHeader("Authorization");
            return token;
        }
        return null;
    }
}
