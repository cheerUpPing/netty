package com.elon.version.four.objectMessage.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 16:43
 * <p>
 * 描述
 */
public class ClientInitializer extends ChannelInitializer {

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("object-decoder", new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
        channelPipeline.addLast("object-encoder", new ObjectEncoder());
        //心跳机制
        channelPipeline.addLast("idle", new IdleStateHandler(0, 0, 6, TimeUnit.SECONDS));
        // 自己的逻辑Handler
        channelPipeline.addLast("handler", new ClientHandler());
    }
}
