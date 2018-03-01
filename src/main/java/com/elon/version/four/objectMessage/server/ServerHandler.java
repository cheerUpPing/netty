package com.elon.version.four.objectMessage.server;

import com.elon.version.four.objectMessage.entity.MsgWapper;
import com.elon.version.util.LogUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 16:51
 * <p>
 * 描述 通道事件驱动器
 */
public class ServerHandler extends SimpleChannelInboundHandler {
    /**
     * 读取到信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgWapper msgWapper = (MsgWapper) msg;
        System.out.println(msgWapper.getData() + "=========" + msgWapper);
    }

    /**
     * 通道建立连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        Server.servers.put(channel.id(), channel);
        System.out.println(Server.servers.size() + "------server------");
        System.out.println("我是服务器，收到客户端【" + channel + "】的连接" + ((InetSocketAddress) (channel.remoteAddress())).getAddress().getHostAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器发生异常：【" + LogUtil.getStackTrace(cause) + "】");
        ctx.writeAndFlush(LogUtil.getStackTrace(cause));
    }
}
