package com.elon.version.four.custom_codec.client;

import com.elon.version.four.custom_codec.codec.MsgDecoder;
import com.elon.version.four.custom_codec.codec.MsgEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

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
        channelPipeline.addLast("msg-decoder", new MsgDecoder());
        channelPipeline.addLast("msg-encoder", new MsgEncoder());
        // 自己的逻辑Handler
        channelPipeline.addLast("handler", new ClientHandler());
    }
}
