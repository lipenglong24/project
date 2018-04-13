package com.lipenglong.ssoclient;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * sso-client
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class LocalSessionUtil {
    private static Map<String, HttpSession> sessionMap = Collections.synchronizedMap(new HashMap<String, HttpSession>());

    public static void addSession(String globalId, HttpSession session) {
        sessionMap.put(globalId, session);
    }

    public static HttpSession getSession(String globalId) {
        return sessionMap.get(globalId);
    }

    public static void delSession(String globalId) {
        sessionMap.remove(globalId);
    }
}
