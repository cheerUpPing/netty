package com.elon.version.four.http.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 16:43
 * <p>
 * 描述
 */
public class ServerInitializer extends ChannelInitializer {

    private boolean isServer = false;

    public ServerInitializer(boolean isServer) {
        this.isServer = isServer;
    }

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        if (isServer) {
            channelPipeline.addLast("server_codec", new HttpServerCodec());
        } else {
            channelPipeline.addLast("server_codec", new HttpClientCodec());
        }
        channelPipeline.addLast("server_aggregator", new HttpObjectAggregator(512 * 1024));
        // 自己的逻辑Handler
        channelPipeline.addLast("handler", new ServerHandler());
    }
}
