package com.sunnick.easyim.local;

import com.sunnick.easyim.entity.IMLoginRequest;
import com.sunnick.easyim.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sunnick on 2019/2/10/010.
 */
@Service
public class LocalLoginService implements LoginService {
    private static Logger logger = LoggerFactory.getLogger(LocalLoginService.class);

    private static Map<String,IMLoginRequest> loginMap = new ConcurrentHashMap<>();

    @Override
    public void login(IMLoginRequest request) {
        loginMap.putIfAbsent(request.getUserid(),request);
    }

    @Override
    public boolean isLogin(String userid) {
        return loginMap.get(userid) != null;
    }

    @Override
    public IMLoginRequest getLoginInfo(String userid){
        return loginMap.get(userid);
    }

    @Override
    public Map<String, IMLoginRequest> getAllLoginUser() {
        return loginMap;
    }


}
