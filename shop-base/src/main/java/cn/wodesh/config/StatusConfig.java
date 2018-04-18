package cn.wodesh.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TS on 2018/4/18.
 */
public class StatusConfig {

    static {
        Map<Integer , String> PRODUCTSTATUS = new HashMap<Integer , String>();
        PRODUCTSTATUS.put(1 , "上架");
        PRODUCTSTATUS.put(2 , "下架");
    }

}
