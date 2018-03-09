package com.lipenglong.ldubbo.config;

/**
 * registry配置类
 * </p>
 * Created by lipenglong on 2017/8/30.
 */
public class RegistryConfig extends AbstractConfig {
    private static final long serialVersionUID = -128869717961737215L;
    private String address;
    private String protocol;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRegistryIp() {
        return address.substring(0, address.indexOf(":"));
    }

    public int getRegistryPort() {
        return Integer.parseInt(address.substring(address.indexOf(":") + 1));
    }

    @Override
    public String toString() {
        return "RegistryConfig{" +
                "address='" + address + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
