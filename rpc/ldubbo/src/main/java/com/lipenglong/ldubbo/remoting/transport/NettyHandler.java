package com.lipenglong.ldubbo.remoting.transport;

import com.lipenglong.ldubbo.remoting.ChannelHandler;
import com.lipenglong.ldubbo.rpc.Invocation;
import com.lipenglong.ldubbo.rpc.LdubboInvoker;
import com.lipenglong.ldubbo.rpc.Result;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * netty handler消息处理类
 * </p>
 * Created by lipenglong on 2017/8/29.
 */
public class NettyHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandler handler;
    private LdubboInvoker invoker;

    public NettyHandler(ChannelHandler handler) {
        this.handler = handler;
    }

    public NettyHandler(ChannelHandler handler, LdubboInvoker invoker) {
        this.handler = handler;
        this.invoker = invoker;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (invoker != null) {
            invoker.setChannel(ctx.channel());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg.getClass().equals(Invocation.class)) {
            Result result = handler.received(msg);
            if (result != null) {
                ctx.writeAndFlush(result);
            }
        } else if (msg.getClass().equals(Result.class)) {
            invoker.addResult((Result) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
