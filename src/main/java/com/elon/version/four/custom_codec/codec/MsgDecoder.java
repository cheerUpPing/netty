package com.elon.version.four.custom_codec.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import seriable.SeriableUtil;

import java.util.List;

/**
 * 2017/9/27 13:41.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 入站
 */
public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() < 4) {//可读字节数，单位 字节
            System.out.println("not suit data head,re read");
            return;
        }
        in.markReaderIndex();
        int receivedContentLength = in.readInt();
        System.out.println("recevied data,the data length is " + receivedContentLength);
        if (in.readableBytes() < receivedContentLength) {//如果可读的数据 没有达到应该获取的数据长度 那么重新读
            System.out.println("the data is not enough");
            return;
        }
        byte[] bytes = new byte[receivedContentLength];
        in.readBytes(bytes);
        Object object = SeriableUtil.byteToObject(bytes);
        out.add(object);
    }
}
