package cn.wodesh.config;

import java.util.HashMap;
import java.util.Map;

public class FastMeilConfig {

    public static final Map<String , String> FASTMEIL_NAME = new HashMap<>();
    /**快递状态*/
    public static final Map<Integer , String> FASTMEIL_SATUS = new HashMap<>();
    static {
        FASTMEIL_NAME.put("YTO" , "圆通速递");
        FASTMEIL_NAME.put("SF" , "顺丰速运");
        FASTMEIL_NAME.put("HTKY" , "百世快递");
        FASTMEIL_NAME.put("ZTO","中通快递");
        FASTMEIL_NAME.put("YD" , "韵达速递");

        FASTMEIL_SATUS.put(2 , "在途中");
        FASTMEIL_SATUS.put(3 , "签收");
        FASTMEIL_SATUS.put(4 , "问题件");
    }
}
