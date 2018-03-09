package com.lipenglong.ldubbo.config;

import com.lipenglong.ldubbo.rpc.Protocol;
import com.lipenglong.ldubbo.rpc.ProtocolFactory;

/**
 * service配置类
 * <p/>
 * Created by lipenglong on 2017/7/24.
 */
public class ServiceConfig<T> extends AbstractConfig {
    private static final long serialVersionUID = 831848002887083456L;
    private String interfaceName;
    private T ref;
    private ProtocolConfig protocolConfig;
    private Class<?> interfaceClass;
    private Protocol protocol;

    public String getInterface() {
        return interfaceName;
    }

    public void setInterface(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    public ProtocolConfig getProtocolConfig() {
        return protocolConfig;
    }

    public void setProtocolConfig(ProtocolConfig protocolConfig) {
        this.protocolConfig = protocolConfig;
    }

    public synchronized void export() {
        try {
            interfaceClass = Class.forName(interfaceName, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        protocol = ProtocolFactory.getProtocol(protocolConfig.getName());
        protocol.export(interfaceClass, ref);
    }

    @Override
    public String toString() {
        return "ServiceConfig{" +
                "interfaceName='" + interfaceName + '\'' +
                ", ref=" + ref +
                ", protocolConfig=" + protocolConfig +
                ", interfaceClass=" + interfaceClass +
                ", protocol=" + protocol +
                '}';
    }
}
