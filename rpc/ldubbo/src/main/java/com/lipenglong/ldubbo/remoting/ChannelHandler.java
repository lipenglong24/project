package com.lipenglong.ldubbo.remoting;


import com.lipenglong.ldubbo.rpc.Result;

/**
 * server端的netty channel处理信息类
 * </p>
 * Created by lipenglong on 2017/8/29.
 */
public interface ChannelHandler {
    /**
     * 接收消息处理方法
     *
     * @param message 消息对象
     * @return 处理结果
     */
    Result received(Object message);
}
