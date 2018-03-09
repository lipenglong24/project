package com.lipenglong.ldubbo.config;

/**
 * protocol配置类
 * <p/>
 * Created by lipenglong on 2017/7/25.
 */
public class ProtocolConfig extends AbstractConfig {
    private static final long serialVersionUID = -5067525426300152084L;
    // 服务协议
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProtocolConfig{" +
                "name='" + name + '\'' +
                '}';
    }
}
