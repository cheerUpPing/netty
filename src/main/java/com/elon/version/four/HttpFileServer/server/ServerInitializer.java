package com.elon.version.four.HttpFileServer.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 2017/5/19 14:14.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("http-decoder", new HttpRequestDecoder()); // 请求消息解码器
        ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));// 目的是将多个消息转换为单一的request或者response对象
        ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());//响应解码器
        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());//目的是支持异步大文件传输（）
        ch.pipeline().addLast("fileServerHandler", new ServerHandler());// 业务逻辑
    }

}
