package com.lipenglong.ssoclient;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * sso-client
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class LocalSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("=========== add session ========== " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("=========== del session ============ " + se.getSession().getId());
    }
}
