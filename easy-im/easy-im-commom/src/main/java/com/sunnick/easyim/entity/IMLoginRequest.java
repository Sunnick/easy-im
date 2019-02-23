package com.sunnick.easyim.entity;

import com.alibaba.fastjson.JSON;

/**
 * Created by Sunnick on 2019/2/10/010.
 */
public class IMLoginRequest {
    private String userid;
    private String username;
    private String serverHost;
    private int nettyPort;
    private int httpPort;

    public IMLoginRequest(String userid, String username, String serverHost, int serverPort,int httpPort) {
        this.userid = userid;
        this.username = username;
        this.serverHost = serverHost;
        this.nettyPort = serverPort;
        this.httpPort = httpPort;
    }

    public IMLoginRequest(){}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
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
    public String toString() {
        return JSON.toJSONString(this);
    }
}
