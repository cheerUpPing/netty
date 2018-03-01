package com.elon.version.four.custom_codec.entity;

/**
 * 2017/9/27 14:27.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class RequestEntity extends Msg {

    public static String mark = "ILoveYou";

    private String command;

    private String token;

    private Object data;

    public static String getMark() {
        return mark;
    }

    public static void setMark(String mark) {
        RequestEntity.mark = mark;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "command='" + command + '\'' +
                ", token='" + token + '\'' +
                ", data=" + data +
                '}';
    }

    public static void main(String[] args) {

    }
}
