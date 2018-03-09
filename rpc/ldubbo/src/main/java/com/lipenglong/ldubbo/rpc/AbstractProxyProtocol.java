package com.lipenglong.ldubbo.rpc;

import com.lipenglong.ldubbo.config.RegistryConfig;

/**
 * AbstractProxyProtocol
 * <p/>
 * Created by lipenglong on 2017/7/27.
 */
public abstract class AbstractProxyProtocol implements Protocol {

    @Override
    public void export(Class interfaceClass, Object ref) {
        doExport(interfaceClass, ref);
    }

    @Override
    public <T> T refer(Class<?> interfaceClass, RegistryConfig registryConfig) {
        return doRefer(interfaceClass, registryConfig);
    }

    protected abstract void doExport(Class interfaceClass, Object ref);

    protected abstract <T> T doRefer(Class interfaceClass, RegistryConfig registryConfig);
}
