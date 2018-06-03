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
    public static final String SENDPAY = "SENDPAY";
    public static final String PAY = "PAY";
    public static final String NOPAY = "NOPAY";
    public static final String PAYCASHATYPISM = "PAYCASHATYPISM";
    public static Map<String , String> PAY_STATUS = new HashMap<>();

    /**支付回调状态*/
    public static final String NO_CONFIRM = "NOCONFIRM";
    public static final String CALL_BACK = "CALLBACK";
    public static final String ACTIVE_SEARCH = "ACTIVESEARCH";
    public static final Map<String , String> PAY_CONFIRM_TYPE = new HashMap<>();
    static {
        PRODUCTSTATUS.put(1 , "上架");
        PRODUCTSTATUS.put(2 , "下架");
        PRODUCTSTATUS.put(3 , "售完");
        PRODUCTSTATUS.put(4 , "超出库存");

        ORDERSTATUS.put(0 , "订单删除");
        ORDERSTATUS.put(1 , "待付款");
        ORDERSTATUS.put(2 , "待发货");
        ORDERSTATUS.put(3 , "待收货");
        ORDERSTATUS.put(4 , "交易完成");
        ORDERSTATUS.put(5 , "交易关闭");


        PAY_TYPE.put(1 , "微信");

        PAY_STATUS.put(SENDPAY,"发起支付");
        PAY_STATUS.put(PAY , "已支付");
        PAY_STATUS.put(NOPAY , "未支付");
        PAY_STATUS.put(PAYCASHATYPISM , "支付金额不一致");

        PAY_CONFIRM_TYPE.put(NO_CONFIRM , "未收到支付确认信息");
        PAY_CONFIRM_TYPE.put(CALL_BACK , "支付回调");
        PAY_CONFIRM_TYPE.put(ACTIVE_SEARCH , "支付主动查询");
    }

}
