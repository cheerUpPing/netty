package com.elon.version.four.objectMessage.client;

import com.elon.version.four.objectMessage.entity.MsgWapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 17:20
 * <p>
 * 描述  对象传输，使用netty自身的 ObjectEncoder对象编码器  ObjectDecoder对象解码器
 * ObjectEncoder对象编码器  ObjectDecoder对象解码器  是netty自身对对象的解码和编码
 */
public class Client {

    public static ConcurrentHashMap<ChannelId, Channel> clients = new ConcurrentHashMap<ChannelId, Channel>();

    public static void connectServer() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ClientInitializer());
        try {
            Channel channel = bootstrap.connect("127.0.0.1", 8001).sync().channel();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                MsgWapper msgWapper = new MsgWapper();
                msgWapper.setData(i + 1);
                channel.writeAndFlush(msgWapper);
            }
            System.out.println("=====================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        connectServer();
    }
}
