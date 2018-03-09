package com.lipenglong.ldubbo.remoting.transport;

import com.lipenglong.ldubbo.remoting.ChannelHandler;
import com.lipenglong.ldubbo.rpc.LdubboInvoker;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Netty客户端类实现
 * </p>
 * Created by lipenglong on 2017/9/1.
 */
public class NettyClient {

    private ChannelHandler handler;
    private LdubboInvoker invoker;

    public NettyClient(String host, int port, ChannelHandler handler, LdubboInvoker invoker) {
        this.handler = handler;
        this.invoker = invoker;
        doConnect(host, port);
    }

    private void doConnect(final String host, final int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EventLoopGroup group = new NioEventLoopGroup();
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast("decoder", MarshallingCodecFactory.buildMarshallingDecoder());
                                ch.pipeline().addLast("encoder", MarshallingCodecFactory.buildMarshallingEncoder());
                                ch.pipeline().addLast(new NettyHandler(handler, invoker));
                            }
                        });
                try {
                    ChannelFuture f = bootstrap.connect(host, port).sync();
                    synchronized (handler) {
                        handler.notify();
                    }
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            synchronized (handler) {
                handler.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
