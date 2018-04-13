package com.lipenglong.ssoclient;

import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * 权限认证interceptor
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class AuthInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String url = PropertiesUtil.getValue("conf.properties", "host") + uri;
        if ("/logout".equals(uri)) {
            String globalId = request.getParameter("globalId");
            if (StringUtils.hasText(globalId)) {
                HttpSession session = LocalSessionUtil.getSession(globalId);
                if (session != null) {
                    LocalSessionUtil.delSession(globalId);
                    session.invalidate();
                }
                return false;
            }
            HttpUtil.sendRedirect(request, response,
                    PropertiesUtil.getValue("conf.properties", "loginUrl"));
            return false;
        }
        if (request.getSession().getAttribute("userInfo") == null) {
            String token = request.getParameter("token");
            if (StringUtils.hasText(token)) {
                String verifyUrl = PropertiesUtil.getValue("conf.properties", "verifyUrl");
                verifyUrl += "?token=" + token + "&localId=" + request.getSession().getId();
                JSONObject result = new JSONObject(HttpUtil.executeGet(verifyUrl));
                if (result.getInt("flag") == 0) {
                    Auth auth = new Auth();
                    auth.setUsername(result.getString("username"));
                    auth.setGlobalId(result.getString("globalId"));
                    request.getSession().setAttribute("userInfo", auth);
                    LocalSessionUtil.addSession(auth.getGlobalId(), request.getSession());
                    HttpUtil.sendRedirect(request, response, url);
                    return false;
                }
            }
            url = URLEncoder.encode(url, "utf-8");
            HttpUtil.sendRedirect(request, response,
                    PropertiesUtil.getValue("conf.properties", "loginUrl") + "?service=" + url);
            return false;
        }
        return true;
    }
}
