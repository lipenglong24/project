package com.lipenglong.ssoserver.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lipenglong.ssoserver.util.HttpUtil.sendRedirect;

/**
 * spring mvc拦截器，判断登录状态
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userInfo") == null) {
            sendRedirect(request, response, "/login");
            return false;
        }
        return true;
    }
}
