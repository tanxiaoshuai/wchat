package cn.wodesh.util;

import java.util.UUID;

public class KeyUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-" , "");
    }
}
