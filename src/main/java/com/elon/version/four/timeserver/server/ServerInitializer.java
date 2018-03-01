package com.elon.version.four.timeserver.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/**
 * 2017/5/15 9:47.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline channelPipeline = ch.pipeline();
        //channelPipeline.addLast(new LineBasedFrameDecoder(1024));
        channelPipeline.addLast(new FixedLengthFrameDecoder(20));
        channelPipeline.addLast(new StringDecoder());
        channelPipeline.addLast(new ServerHandler());
    }
}
