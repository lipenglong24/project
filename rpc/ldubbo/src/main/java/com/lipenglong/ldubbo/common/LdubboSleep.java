package com.lipenglong.ldubbo.common;

/**
 * ldubbo协议代理类sleep调用类
 * </p>
 * Created by lipenglong on 2017/9/5.
 */
public class LdubboSleep {
    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
