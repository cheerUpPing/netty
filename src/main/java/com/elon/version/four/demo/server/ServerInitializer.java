package com.elon.version.four.demo.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
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

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //// 以("\n")为结尾分割的 解码器
        channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 字符串解码 和 编码
        channelPipeline.addLast("decoder", new StringDecoder(Charset.forName("utf-8")));
        channelPipeline.addLast("encoder", new StringEncoder(Charset.forName("utf-8")));
        // 自己的逻辑Handler
        channelPipeline.addLast("handler",new ServerHandler());
    }
}
