package com.elon.version.four.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * 2017/10/9 14:41.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class Server {

    public static void openUdpServer(int port) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();//udp不能使用ServerBootstrap
            b.group(workerGroup).channel(NioDatagramChannel.class)//设置UDP通道
                    .handler(new UdpServerInitializer())//初始化处理器
                    .option(ChannelOption.SO_BROADCAST, true)// 支持广播
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024)// 设置UDP读缓冲区为1M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024);// 设置UDP写缓冲区为1M

            System.out.println("[UDP 服务端启动了]");

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();

            // 等待服务器 socket 关闭 。
            // 这不会发生，可以优雅地关闭服务器。
            f.channel().closeFuture().await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            System.out.println("[UDP 关闭了]");
        }
    }

    public static void main(String[] args) {
        openUdpServer(8081);
    }

}
