package com.elon.version.four.timeserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * 2017/5/15 9:33.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class TimeServer {


    private static int port = 8001;

    public static void startServer() {

        EventLoopGroup parent = new NioEventLoopGroup();
        EventLoopGroup children = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parent, children);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        serverBootstrap.childHandler(new ServerInitializer());

        ChannelFuture future = null;
        try {
            future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parent.shutdownGracefully();
            children.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        startServer();
    }

}
