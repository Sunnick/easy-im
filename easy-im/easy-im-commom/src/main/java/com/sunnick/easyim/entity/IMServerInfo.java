package com.sunnick.easyim.entity;

import com.alibaba.fastjson.JSON;

/**
 * Created by Sunnick on 2019/1/29/029.
 * 服务器相关信息
 */
public class IMServerInfo {
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
