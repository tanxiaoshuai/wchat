package cn.wodesh.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TS on 2018/4/18.
 */
public class StatusConfig {

    public static Map<Integer , String> PRODUCTSTATUS = new HashMap<Integer , String>();
    static {
        PRODUCTSTATUS.put(1 , "上架");
        PRODUCTSTATUS.put(2 , "下架");
        PRODUCTSTATUS.put(3 , "售完");
        PRODUCTSTATUS.put(4 , "超出库存");
    }

}
