package com.sunnick.easyim.entity;

/**
 * Created by Sunnick on 2019/2/10/010.
 */
public abstract class BaseResponseEntity {
    /**
     * 返回码,0000表示成功
     */
    private String code = "0000";
    /**
     * 返回消息
     */
    private String msg;

    /**
     * 判断是否操作成功，0000表示成功
     */
    public boolean success(){
        return "0000".equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
