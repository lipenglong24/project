package com.lipenglong.ssoserver.listener;

import com.lipenglong.ssoserver.util.TokenUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.util.List;

import static com.lipenglong.ssoserver.util.HttpUtil.executeGet;

/**
 * 全局session listener
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class GlobalSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("=========== add session ========== " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("=========== del session ============ " + se.getSession().getId());
        String token = TokenUtil.getToken(se.getSession().getId());
        if (token != null) {
            List<String> clientList = TokenUtil.getTokenInfo(token).getSsoClientList();
            try {
                for (String client : clientList) {
                    executeGet(client + "/logout?globalId=" + se.getSession().getId());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TokenUtil.delTokenInfo(token);
        }
    }
}
