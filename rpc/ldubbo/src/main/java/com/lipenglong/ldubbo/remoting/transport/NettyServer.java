package com.lipenglong.ldubbo.remoting.transport;

import com.lipenglong.ldubbo.remoting.ChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Netty服务端类实现
 * </p>
 * Created by lipenglong on 2017/8/29.
 */
public class NettyServer {
    private int port;
    private ChannelHandler handler;

    public NettyServer(Integer port, ChannelHandler requestHandler) {
        this.port = port;
        this.handler = requestHandler;
        doOpen();
    }

    private void doOpen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 100)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast("decoder", MarshallingCodecFactory.buildMarshallingDecoder());
                                ch.pipeline().addLast("encoder", MarshallingCodecFactory.buildMarshallingEncoder());
                                ch.pipeline().addLast(new NettyHandler(handler));
                            }
                        });
                try {
                    ChannelFuture f = b.bind(port).sync();
                    synchronized (handler) {
                        handler.notify();
                    }
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
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
