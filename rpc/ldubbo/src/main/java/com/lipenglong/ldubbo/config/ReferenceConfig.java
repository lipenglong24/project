package com.lipenglong.ldubbo.config;

import com.lipenglong.ldubbo.rpc.Protocol;
import com.lipenglong.ldubbo.rpc.ProtocolFactory;

/**
 * com.lipenglong.ldubbo.config.ReferenceConfig
 * </p>
 * Created by lipenglong on 2017/8/30.
 */
public class ReferenceConfig<T> extends AbstractConfig {
    private static final long serialVersionUID = -226929659993307561L;
    private String interfaceName;
    private RegistryConfig registryConfig;
    private Class<?> interfaceClass;
    private Protocol protocol;
    private transient volatile T ref;

    public String getInterface() {
        return interfaceName;
    }

    public void setInterface(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public RegistryConfig getRegistryConfig() {
        return registryConfig;
    }

    public void setRegistryConfig(RegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }

    public T get() {
        if (ref == null) {
            init();
        }
        return ref;
    }

    private void init() {
        try {
            interfaceClass = Class.forName(interfaceName, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ref = createProxy();
    }

    private T createProxy() {
        protocol = ProtocolFactory.getProtocol(registryConfig.getProtocol());
        return (T) protocol.refer(interfaceClass, registryConfig);
    }

    public Class<?> getInterfaceClass() {
        if (interfaceClass != null) {
            return interfaceClass;
        }
        try {
            interfaceClass = Class.forName(interfaceName, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return interfaceClass;
    }
}
