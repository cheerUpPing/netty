package com.elon.version.util;

import java.io.*;

/**
 * 2017/9/27 14:41.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 序列化工具
 */
public class SeriableUtil {

    /**
     * 对象转字节数组
     *
     * @param object
     * @return
     * @throws IOException
     */
    public static byte[] objectToByte(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 字节数组转对象
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object byteToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }

}
