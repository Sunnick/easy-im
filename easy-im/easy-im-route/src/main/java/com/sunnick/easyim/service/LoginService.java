package com.sunnick.easyim.service;

import com.sunnick.easyim.entity.IMLoginRequest;

import java.util.Map;

/**
 * Created by Sunnick on 2019/2/10/010.
 */
public interface LoginService {
    void login(IMLoginRequest request);
    boolean isLogin(String userid);
    IMLoginRequest getLoginInfo(String userid);
    Map<String, IMLoginRequest> getAllLoginUser();
    void logout(String  userid);
}
