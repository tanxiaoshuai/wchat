package cn.wodesh.util;

import cn.wodesh.config.AppConfig;

import java.util.UUID;

public class KeyUtil {

    public static String tokenKey(String userid){
        return new StringBuffer().append(AppConfig.TOKEN_PREFIX).append(userid).toString();
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-" , "");
    }
}
