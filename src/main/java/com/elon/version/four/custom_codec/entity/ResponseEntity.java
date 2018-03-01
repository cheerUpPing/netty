package com.elon.version.four.custom_codec.entity;

/**
 * 2017/9/27 14:30.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ResponseEntity extends Msg {


    private String result = "0000";

    private Object data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "result='" + result + '\'' +
                ", data=" + data +
                "} ";
    }
}
