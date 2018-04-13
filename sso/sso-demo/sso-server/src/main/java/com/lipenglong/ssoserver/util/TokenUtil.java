package com.lipenglong.ssoserver.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class TokenUtil {
    //TokenInfo对象map，key为token值
    private static Map<String, TokenInfo> tokenInfoMap = Collections.synchronizedMap(new HashMap<String, TokenInfo>());
    //token值map，key为sessionId
    private static Map<String, String> tokenMap = Collections.synchronizedMap(new HashMap<String, String>());

    public static void setToken(String token, TokenInfo tokenInfo) {
        tokenInfoMap.put(token, tokenInfo);
        tokenMap.put(tokenInfo.getGlobalId(), token);
    }

    public static TokenInfo getTokenInfo(String token) {
        return tokenInfoMap.get(token);
    }

    public static void delTokenInfo(String token) {
        TokenInfo tokenInfo = getTokenInfo(token);
        tokenMap.remove(tokenInfo.getGlobalId());
        tokenInfoMap.remove(token);
    }

    public static String getToken(String globalId) {
        return tokenMap.get(globalId);
    }
}