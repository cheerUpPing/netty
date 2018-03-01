package com.elon.version.four.udp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;


/**
 * 2017/10/9 14:58.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * udp消息处理
 */
public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

        // 读取收到的数据
        ByteBuf buf = msg.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, CharsetUtil.UTF_8);
        System.out.println(">>>>>> 收到服务端" + msg.sender().getHostString() + ":" + msg.sender().getPort() + "的数据：" + body);
        // 消息处理。。。。。

        //消息发送。。。。
        //String back = "返回的信息";
        //DatagramPacket dp = new DatagramPacket(Unpooled.copiedBuffer(back.getBytes()), msg.sender());
        //ctx.writeAndFlush(dp);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活");
        String msg = "你好码";
        DatagramPacket datagramPacket = new DatagramPacket(Unpooled.copiedBuffer(msg.getBytes()), new InetSocketAddress("127.0.0.1", 8081));
        ctx.writeAndFlush(datagramPacket);
        //TimeUnit.SECONDS.sleep(10);
        //ctx.close();关闭udp服务
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭");
    }
}
