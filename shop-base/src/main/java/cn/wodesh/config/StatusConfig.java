package cn.wodesh.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TS on 2018/4/18.
 */
public class StatusConfig {

    public static Map<Integer , String> PRODUCTSTATUS = new HashMap<>();
    public static Map<Integer , String> ORDERSTATUS = new HashMap<>();
    static {
        PRODUCTSTATUS.put(1 , "上架");
        PRODUCTSTATUS.put(2 , "下架");
        PRODUCTSTATUS.put(3 , "售完");
        PRODUCTSTATUS.put(4 , "超出库存");

        ORDERSTATUS.put(0 , "商家待接单");
        ORDERSTATUS.put(1 , "待付款");
        ORDERSTATUS.put(2 , "待发货");
        ORDERSTATUS.put(3 , "待收货");
        ORDERSTATUS.put(4 , "交易完成");
        ORDERSTATUS.put(5 , "交易关闭");
    }

}
