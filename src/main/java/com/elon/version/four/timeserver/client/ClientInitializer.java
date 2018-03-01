package com.elon.version.four.timeserver.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/**
 * 2017/5/15 10:29.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        /**行分隔符解码器，所以发送的数据必须有行分隔符，要不一直等待行分隔符才返回数据 System.getProperty("line.separator")**/
        /**这里也可以自定义 channelPipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("##".getBytes()))) 分隔符**/
        //channelPipeline.addLast(new LineBasedFrameDecoder(1024));
        channelPipeline.addLast(new FixedLengthFrameDecoder(20));
        channelPipeline.addLast(new StringDecoder());
        channelPipeline.addLast(new ClientHandler());
    }
}
