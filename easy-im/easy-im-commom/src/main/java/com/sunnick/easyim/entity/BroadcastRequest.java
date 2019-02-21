package com.sunnick.easyim.entity;

/**
 * Created by Sunnick on 2019/2/10/010.
 */
public class BroadcastRequest {
    private String fromUserId;
    private String msg;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
