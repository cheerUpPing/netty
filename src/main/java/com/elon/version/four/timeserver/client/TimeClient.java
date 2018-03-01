package com.elon.version.four.timeserver.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2017/5/15 10:22.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class TimeClient {

    public static String remote_url = "127.0.0.1";

    public static int port = 8001;

    public static void startClient() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.handler(new ClientInitializer());

        ChannelFuture future = null;
        Channel channel = null;
        try {
            future = bootstrap.connect(remote_url, port).sync();
            channel = future.channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String line = bufferedReader.readLine();
                if (line == null || "".equals(line) || "".equals(line.trim())) {
                    continue;
                }
                //byte[] source = (line + System.getProperty("line.separator")).getBytes();
                byte[] source = line.getBytes();
                for (int i = 1; i<= 10;i++){
                    ByteBuf byteBuf = Unpooled.buffer(source.length);
                    byteBuf.writeBytes(source);
                    channel.writeAndFlush(byteBuf);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        System.out.println("-"+System.getProperty("line.separator"));
        startClient();
    }
}
