package com.lipenglong.ldubbo.rpc;

import io.netty.channel.Channel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ldubbo协议client端发送信息，接收信息类
 * </p>
 * Created by lipenglong on 2017/9/1.
 */
public class LdubboInvoker {
    //server端调用返回的结果存储map
    private static Map<String, Result> resultMap = Collections
            .synchronizedMap(new HashMap<String, Result>());
    //netty的通道
    private Channel channel;

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * 接收到Invocation对象时，通过channel写给server端
     *
     * @param invocation client端发送的请求对象
     */
    public void addInvocation(Invocation invocation) {
        channel.writeAndFlush(invocation);
    }

    public void addResult(Result msg) {
        resultMap.put(msg.serialNo(), msg);
    }

    public Result takeResult(String serialNo) {
        Result result = resultMap.get(serialNo);
        if (result != null) {
            resultMap.remove(serialNo);
        }
        return result;
    }
}
