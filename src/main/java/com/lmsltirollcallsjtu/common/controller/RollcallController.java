package com.lmsltirollcallsjtu.common.controller;

import com.lmsltirollcallsjtu.common.bean.bo.UserInfo;
import com.lmsltirollcallsjtu.common.utils.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author huyong
 * @date 2019-10-31
 * @Description:老师发起点名controller
 */
@Controller
public class RollcallController {
    @Autowired
    private UserInfo userInfo;
    //通过此注解映射/welcome这个地址
    @MessageMapping("/welcome")
    //当服务器有消息会对订阅了@SendTo中的路径的浏览器发送消息
    @SendTo("/topic/getResponse")
    public String sendToSecret(@PathVariable Long courseId){
        String secret = SecretUtil.generateSecret(userInfo);
        return secret;
    }
}
