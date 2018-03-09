package com.lipenglong.ldubbo.rpc.protocol;

import com.lipenglong.ldubbo.config.RegistryConfig;
import com.lipenglong.ldubbo.remoting.ChannelHandler;
import com.lipenglong.ldubbo.remoting.transport.NettyClient;
import com.lipenglong.ldubbo.remoting.transport.NettyServer;
import com.lipenglong.ldubbo.rpc.AbstractProxyProtocol;
import com.lipenglong.ldubbo.rpc.Invocation;
import com.lipenglong.ldubbo.rpc.LdubboInvoker;
import com.lipenglong.ldubbo.rpc.Result;
import com.lipenglong.ldubbo.rpc.proxy.ProxyFactory;
import io.netty.channel.Channel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Ldubbo Protocol协议通信类，基于netty的发布服务和引用服务
 * <p/>
 * Created by lipenglong on 2017/8/29.
 */
public class LdubboProtocol extends AbstractProxyProtocol {
    //ldubbo协议提供服务的默认端口
    private static final Integer DEFAULT_PORT = 30880;
    //提供服务的service实例对象map，即spring配置文件中ref引用的服务实现类对象map，key为interface接口名
    private static final Map<String, Object> instanceMap = new ConcurrentHashMap<String, Object>();
    //netty server线程是否启动，当配置多个ldubbo service时，在默认端口只启动一个netty server线程
    private static transient Boolean isOpen = false;

    private ChannelHandler requestHandler = new ChannelHandler() {
        @Override
        public Result received(Object message) {
            Result result = null;
            Invocation inv = (Invocation) message;
            Object instance = instanceMap.get(inv.getServiceKey());
            try {
                Method method = instance.getClass().getMethod(inv.getMethodName(), inv.getParameterTypes());
                Object obj = method.invoke(instance, inv.getArguments());
                result = new Result(obj, inv.getSerialNo());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return result;
        }
    };

    @Override
    protected void doExport(Class interfaceClass, Object ref) {
        instanceMap.put(interfaceClass.getName(), ref);
        if (!isOpen) {
            new NettyServer(DEFAULT_PORT, requestHandler);
            isOpen = true;
        }
    }

    @Override
    protected <T> T doRefer(Class interfaceClass, RegistryConfig registryConfig) {
        LdubboInvoker ldubboInvoker = new LdubboInvoker();
        new NettyClient(registryConfig.getRegistryIp(), registryConfig.getRegistryPort(), requestHandler, ldubboInvoker);
        T proxy = ProxyFactory.createProxy(interfaceClass, ldubboInvoker);
        return proxy;
    }
}
