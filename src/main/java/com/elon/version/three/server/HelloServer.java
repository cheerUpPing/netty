package com.elon.version.three.server;

import com.elon.version.three.entity.MsgWapper;
import com.elon.version.three.entity.User;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * 2017/5/12 10:46.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 服务端
 */
public class HelloServer {

    private static ConcurrentHashMap<String,Channel> channelConcurrentHashMap = new ConcurrentHashMap<String, Channel>();


    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new ObjectDecoder(), new ServiceHandler());
            }
        });
        serverBootstrap.bind(new InetSocketAddress(8001));
    }

    /**
     * 服务端连接处理类
     */
    private static class ServiceHandler extends SimpleChannelHandler {
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("我是服务端，我已经被客户端连接了");
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            System.out.println("我是服务端，我已经接收到数据了" + e.getChannel());
            MsgWapper msgWapper = (MsgWapper) e.getMessage();
            User user = (User) msgWapper.getData();
            channelConcurrentHashMap.put(msgWapper.getAction(),e.getChannel());
            System.out.println(msgWapper);
        }

        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("客户端关闭了连接");
        }

        @Override
        public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("客户端断开了连接");
        }

        /**
         * 抓取异常
         *
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            System.out.println("异常" + e.getCause().getMessage());
        }
    }
}
