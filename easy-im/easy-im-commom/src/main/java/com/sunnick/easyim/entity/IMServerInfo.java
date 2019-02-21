package com.sunnick.easyim.entity;

import com.alibaba.fastjson.JSON;

/**
 * Created by Sunnick on 2019/1/29/029.
 * 服务器相关信息
 */
public class IMServerInfo {
    private String host;
    private int nettyPort;
    private int httpPort;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getNettyPort() {
        return nettyPort;
    }

    public void setNettyPort(int nettyPort) {
        this.nettyPort = nettyPort;
    }
    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
