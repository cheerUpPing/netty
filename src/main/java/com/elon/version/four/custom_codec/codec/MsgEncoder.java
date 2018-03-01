package com.elon.version.four.custom_codec.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import seriable.SeriableUtil;

/**
 * 2017/9/27 14:36.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 出站
 */
public class MsgEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] bytes = SeriableUtil.objectToByte(msg);
        int contentLength = bytes.length;
        System.out.println("start to transfer data, the length is " + contentLength);
        out.writeInt(contentLength);
        out.writeBytes(bytes);
    }
}
