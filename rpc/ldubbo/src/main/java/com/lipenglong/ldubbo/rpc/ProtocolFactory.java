package com.lipenglong.ldubbo.rpc;

import com.lipenglong.ldubbo.rpc.protocol.LdubboProtocol;

import java.util.ServiceLoader;

/**
 * com.lipenglong.ldubbo.rpc.ProtocolFactory
 * </p>
 * Created by lipenglong on 2017/9/7.
 */
public class ProtocolFactory {
    private static final ServiceLoader<Protocol> loader = ServiceLoader.load(Protocol.class);

    public static Protocol getProtocol(String name) {
        for (Protocol protocol : loader) {
            if (protocol.getClass().getSimpleName().toLowerCase().startsWith(name)) {
                return protocol;
            }
        }
        return new LdubboProtocol();
    }
}
