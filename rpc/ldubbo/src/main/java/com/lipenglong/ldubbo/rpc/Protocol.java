package com.lipenglong.ldubbo.rpc;

import com.lipenglong.ldubbo.config.RegistryConfig;

/**
 * rpc协议接口类
 * <p/>
 * Created by lipenglong on 2017/7/25.
 */
public interface Protocol {
    /**
     * 暴露远程服务
     *
     * @param interfaceClass 服务接口class
     * @param ref            服务接口的实现
     */
    void export(Class<?> interfaceClass, Object ref);

    /**
     * 引用远程服务
     *
     * @param interfaceClass 服务接口class
     * @param registryConfig registry配置
     * @return 服务接口的代理类
     */
    <T> T refer(Class<?> interfaceClass, RegistryConfig registryConfig);
}
