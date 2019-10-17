package com.lmsltirollcallsjtu.common.interceptor;//package com.lmsauthsjtu.common.interceptor;
//
//
//import com.lmsauthsjtu.common.enums.BusinessExceptionEnum;
//import com.lmsauthsjtu.common.exception.BusinessException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *关于Session的自定义拦截器
// **/
//public class RedisSessionInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    /**
//     * @description Controller方法处理之前执行，拦截登录失效的请求
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        //无论访问的地址是不是正确的，都进行登录验证，登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
//        HttpSession session = request.getSession();
//        if (session.getAttribute("loginUserId") != null) {
//                //验证当前请求的session是否是已登录的session，如果是则允许通过
//                String loginSessionId = redisTemplate.opsForValue().get("loginUser:" + (long) session.getAttribute("loginUserId"));
//                if (loginSessionId != null && loginSessionId.equals(session.getId())) {
//                    return true;
//                }
//        }
//
//        try{
//            //抛出用户未登录异常，交给全局异常处理响应提示
//            throw BusinessException.getInstance(BusinessExceptionEnum.NEED_LOGIN);
//        }finally {
//            //不允许通过
//            return false;
//        }
//    }
//
//    /**
//     * @description Controller方法处理完之后，且DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作（调用前提：preHandle返回true）
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    /**
//     * @description DispatcherServlet进行视图的渲染之后执行，多用于清理资源（调用前提：preHandle返回true）
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}