package com.elon.version.three.client;

import com.elon.version.three.entity.MsgWapper;
import com.elon.version.three.entity.User;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


/**
 * 2017/5/12 10:46.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 客户端
 */
public class HelloClient {

    public static void main(String[] args) {
        ClientBootstrap clientBootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new ObjectEncoder(), new ClientHandler());
            }
        });

        /**连接服务器**/
        clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 8001));
    }

    /**
     * 客户端通道事件处理
     */
    private static class ClientHandler extends SimpleChannelHandler {

        /**
         * 绑定到服务端的时候触发
         *
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("我是客户端，我已经连接上了服务端,准备发送数据");
            Channel channel = e.getChannel();
            User user = new User();
            user.setName("徐贵平");
            user.setAge(25);
            MsgWapper msgWapper = new MsgWapper();
            msgWapper.setData(user);
            msgWapper.setAction("4");
            channel.write(msgWapper);
        }



    }
}
