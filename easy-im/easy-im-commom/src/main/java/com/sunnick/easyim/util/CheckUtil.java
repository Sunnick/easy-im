package com.sunnick.easyim.util;

import org.springframework.util.ObjectUtils;

/**
 * Created by Sunnick on 2019/2/10/010.
 */
public class CheckUtil {
    /**
     * 判断所有字段非空
     * 如有一个或多个字段为空，则返回false
     * 全部非空返回true
     * @param objs
     * @return
     */
    public static final boolean isAllNotEmpty(Object... objs){
        for (Object object : objs){
            if (ObjectUtils.isEmpty(object)){
                return false;
            }
        }
        return true;
    }
}
