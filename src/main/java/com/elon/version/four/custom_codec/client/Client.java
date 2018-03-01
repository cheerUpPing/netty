package com.elon.version.four.custom_codec.client;

import com.elon.version.four.custom_codec.entity.RequestEntity;
import com.elon.version.four.custom_codec.entity.ResponseEntity;
import com.elon.version.four.demo.entity.MsgWapper;
import com.elon.version.three.entity.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 17:20
 * <p>
 * 描述  对象传输，使用netty自身的 ObjectEncoder对象编码器  ObjectDecoder对象解码器
 * ObjectEncoder对象编码器  ObjectDecoder对象解码器  是netty自身对对象的解码和编码
 */
public class Client {

    public static void connectServer(String ip, int port) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ClientInitializer());
        try {
            Channel channel = bootstrap.connect(ip, port).sync().channel();
            RequestEntity requestEntity = new RequestEntity();
            requestEntity.setCommand("A0001");
            Map<String,String> map = new HashMap<String, String>();
            map.put("000","0");
            map.put("001","1");
            requestEntity.setData(map);
            requestEntity.setToken("1212122");
            channel.writeAndFlush(requestEntity);
            System.out.println("-------------------"+requestEntity.hashCode());
            TimeUnit.SECONDS.sleep(10);
            ResponseEntity responseEntity = new ResponseEntity();
            responseEntity.setResult("1111");
            responseEntity.setData(new User());
            channel.writeAndFlush(responseEntity);
            System.out.println("---------------------"+responseEntity.hashCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        connectServer("127.0.0.1",8081);
    }
}
