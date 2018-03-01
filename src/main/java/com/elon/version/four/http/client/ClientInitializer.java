package com.elon.version.four.http.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 16:43
 * <p>
 * 描述
 */
public class ClientInitializer extends ChannelInitializer {

    private boolean isServer;

    public ClientInitializer(boolean isServer) {
        this.isServer = isServer;
    }

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        if (isServer) {
            channelPipeline.addLast("client_codec", new HttpServerCodec());
        } else {
            channelPipeline.addLast("client_codec", new HttpClientCodec());
        }
        channelPipeline.addLast("client_aggregator", new HttpObjectAggregator(512 * 1024));
        // 自己的逻辑Handler
        channelPipeline.addLast("handler", new ClientHandler());
    }
}
