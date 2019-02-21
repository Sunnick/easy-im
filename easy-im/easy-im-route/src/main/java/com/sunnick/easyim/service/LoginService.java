package com.sunnick.easyim.service;

import com.sunnick.easyim.entity.IMLoginRequest;

/**
 * Created by Sunnick on 2019/2/10/010.
 */
public interface LoginService {
    void login(IMLoginRequest request);
    boolean isLogin(String userid);
    IMLoginRequest getLoginInfo(String userid);
}
