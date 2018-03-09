package com.lipenglong.ldubbo.rpc.protocol;

import com.lipenglong.ldubbo.config.RegistryConfig;
import com.lipenglong.ldubbo.rpc.AbstractProxyProtocol;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;

/**
 * rmi协议通信类
 * <p/>
 * Created by lipenglong on 2017/7/25.
 */
public class RmiProtocol extends AbstractProxyProtocol {

    private static final Integer DEFAULT_PORT = 1099;

    @Override
    protected void doExport(Class interfaceClass, Object ref) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setRegistryPort(DEFAULT_PORT);
        rmiServiceExporter.setServiceName(interfaceClass.getName());
        rmiServiceExporter.setServiceInterface(interfaceClass);
        rmiServiceExporter.setService(ref);
        try {
            rmiServiceExporter.afterPropertiesSet();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected <T> T doRefer(Class interfaceClass, RegistryConfig registryConfig) {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://" + registryConfig.getAddress() +
                "/" + interfaceClass.getName());
        rmiProxyFactoryBean.setServiceInterface(interfaceClass);
        rmiProxyFactoryBean.setCacheStub(true);
        rmiProxyFactoryBean.setLookupStubOnStartup(true);
        rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
        rmiProxyFactoryBean.afterPropertiesSet();
        return (T) rmiProxyFactoryBean.getObject();
    }
}
