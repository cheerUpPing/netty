package com.elon.version.four.demo.entity;

import java.io.Serializable;

/**
 * Email cheerupping@163.com
 * Time  2017/5/13 17:09
 * <p>
 * 描述 消息包裹类
 */
public class MsgWapper implements Serializable{

    private String from;

    private String to;

    private Object data;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MsgWapper{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", data=" + data +
                '}';
    }
}
