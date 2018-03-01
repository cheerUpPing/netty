package com.elon.version.four.timeserver.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2017/5/15 9:43.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端：客户端和我建立了连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        count = count + 1;
        String request = (String) msg;
        //String respondBody = Command.QUERY_TIME.getCom().equalsIgnoreCase(request) ? sdf.format(new Date()) + System.getProperty("line.separator") : "your commond maybe wrong" + System.getProperty("line.separator");
        String respondBody = Command.QUERY_TIME.getCom().equalsIgnoreCase(request) ? sdf.format(new Date()) : "your commond maybe wrong";
        ByteBuf resp = Unpooled.copiedBuffer(respondBody.getBytes());
        ctx.writeAndFlush(resp);
        System.out.println("服务端：收到来自客户端的信息【" + request + "】,count:【" + count + "】");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("服务端：准备关闭通道【" + channel + "】");
        ctx.close();
    }
}
