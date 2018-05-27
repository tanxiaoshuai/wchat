package cn.wodesh.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TS on 2018/4/18.
 */
public class StatusConfig {

    /**默认地址状态**/
    public static Integer ADDRES_DEFUALT = 1;
    public static Integer ADDRES_NO_DEFUALT = 0;
    public static Map<Integer , String> PRODUCTSTATUS = new HashMap<>();
    public static Map<Integer , String> ORDERSTATUS = new HashMap<>();
    public static Map<Integer , String> PAY_TYPE = new HashMap<>();
    /**支付状态*/
    public static String[] PAY_STATUS = new String[]{"PAY","NOPAY"};
    static {
        PRODUCTSTATUS.put(1 , "上架");
        PRODUCTSTATUS.put(2 , "下架");
        PRODUCTSTATUS.put(3 , "售完");
        PRODUCTSTATUS.put(4 , "超出库存");

        ORDERSTATUS.put(1 , "待付款");
        ORDERSTATUS.put(2 , "待发货");
        ORDERSTATUS.put(3 , "待收货");
        ORDERSTATUS.put(4 , "交易完成");
        ORDERSTATUS.put(5 , "交易关闭");

        PAY_TYPE.put(1 , "微信");
    }

}
