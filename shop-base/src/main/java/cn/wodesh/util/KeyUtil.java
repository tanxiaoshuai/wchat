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

    public static String getKeyToId(String key){
        String[] arr = key.split("_");
        return arr[arr.length - 1];
    }

    public static String orderNoPayKey(String uuid){
        return new StringBuffer().append(AppConfig.ORDER_NO_PAY_KEY).append(uuid).toString();
    }

    public static String fastMeil(String expnumber , String expcode){
        return new StringBuffer().append(AppConfig.FASTMEIL_KEY).append(expcode).append("_").append(expnumber).toString();
    }


    public static String uuid(){
        return UUID.randomUUID().toString().replace("-" , "");
    }
}
