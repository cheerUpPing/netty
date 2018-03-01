package com.elon.version.four.objectMessage.client;

import com.elon.version.four.objectMessage.entity.MsgWapper;
import com.elon.version.util.LogUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 16:51
 * <p>
 * 描述 通道事件驱动器
 */
public class ClientHandler extends SimpleChannelInboundHandler {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * 读取到信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        MsgWapper msgWapper = (MsgWapper) msg;
        System.out.println("我是客户端，收到服务器【" + channel + "】信息【" + msgWapper + "】");
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
        Client.clients.put(channel.id(), channel);
        System.out.println("我是客户端，已成功和服务器建立连接【" + channel + "】");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端发生异常：【" + LogUtil.getStackTrace(cause) + "】");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=========================");
    }

    /**
     * 每次超时时间触发，就向服务器发送心跳，服务器接收到心跳后就回复心跳，然后客户端根据收到的信息确实是否连接正常
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        MsgWapper msgWapper = new MsgWapper();
        msgWapper.setFrom("system_heart");
        ctx.writeAndFlush(msgWapper);
        System.out.println(sdf.format(new Date()) + "===================触发了心跳");
    }
}
