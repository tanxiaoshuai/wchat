package cn.wodesh.util;

import cn.wodesh.config.AppConfig;
import com.sun.org.apache.regexp.internal.RE;

import java.util.UUID;

public class KeyUtil {

    public static String tokenKey(String userid){
        return new StringBuffer().append(AppConfig.TOKEN_PREFIX).append(userid).toString();
    }

    public static String outTradeNoKey(String uuid){
        return new StringBuffer().append(AppConfig.OUT_TRADE_NO).append(uuid).toString();
    }

    public static String getOutTradeNo(String key){
        String[] arr = key.split("_");
        return arr[arr.length - 1];
    }


    public static String uuid(){
        return UUID.randomUUID().toString().replace("-" , "");
    }
}
