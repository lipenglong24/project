package com.lipenglong.ssoserver.util;

import java.util.ArrayList;
import java.util.List;

/**
 * token信息对象
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class TokenInfo {
    private String username;
    private String globalId;
    private List<String> ssoClientList = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public List<String> getSsoClientList() {
        return ssoClientList;
    }

    public void setSsoClientList(List<String> ssoClientList) {
        this.ssoClientList = ssoClientList;
    }

    public void addClient(String ssoClient) {
        ssoClientList.add(ssoClient);
    }
}
