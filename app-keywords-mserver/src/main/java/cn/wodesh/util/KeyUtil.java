package cn.wodesh.util;

import java.util.UUID;

/**
 * Created by TS on 2018/4/28.
 */
public class KeyUtil {

    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
