package com.elon.version.test;

import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * 2017/5/18 17:37.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ProtoMain {

    public static void main(String[] args) throws IOException {
        MessagePack messagePack = new MessagePack();
        UserInfo userInfo = new UserInfo();
        userInfo.setName("xu xian sheng");
        userInfo.setAge(20);
        byte[] bytes = messagePack.write(userInfo);
        System.out.println(bytes.hashCode());
        UserInfo u = null;
        u = messagePack.read(bytes,UserInfo.class);
        System.out.println(u);

    }
}
