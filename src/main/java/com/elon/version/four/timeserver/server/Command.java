package com.elon.version.four.timeserver.server;

/**
 * 2017/5/15 10:03.
 * <p>
 * Email: cheerUpPing@163.com
 */
public enum Command {

    QUERY_TIME("query_time");

    private String com = null;

    Command(String com){
        this.com = com;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
