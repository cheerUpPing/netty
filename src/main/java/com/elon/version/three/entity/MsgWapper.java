package com.elon.version.three.entity;

import java.io.Serializable;

/**
 * 2017/5/12 14:57.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class MsgWapper implements Serializable {

    private String action;

    private String msg;

    private Object data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
                "action='" + action + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
