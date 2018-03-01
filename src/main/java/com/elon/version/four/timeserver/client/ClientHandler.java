package com.elon.version.four.timeserver.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 2017/5/15 10:32.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端：服务端和我建立了连接");
        String req = "wwwwww";
        byte[] bytes = req.getBytes();
        ByteBuf byteBuf = Unpooled.buffer(bytes.length);
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        count = count + 1;
        String response = (String) msg;
        System.out.println("来自服务端回复：" + response + "---【" + count + "】");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
