package cn.wodesh.config;

/**
 * Created by TS on 2018/2/24.
 */
public enum ResultInfo {

    SUCCESS("0000","操作成功"),
    ERROR_PARAM("0001","参数错误"),
    EXCEPTION("0002","系统异常"),
    NOAUTHORIZE("0003" , "未授权"),
    LOGINOUTTIME("0004" , "登录超时"),
    TOKENCHECKERROR("0005" , "认证失败"),
    PAY_CALLBACK_ERROR("0006" , "微信支付回调验签失败"),
    PAY_ORDER_ERROR("0007" , "微信预付订单失败"),
    PAY_ORDER_SIGN_ERROR("0008" , "微信预付订单验签失败"),
    SHOPCAR_CHOICE_ERROR("0009" , "你选择的商品含有售完、下架或库存不足等商品，请重新选择"),
    SHOPCAR_BY_ORDER_OUT_TIME("0010" , "页面失效，确认返回购物车！"),
    SHOPCAR_OUT_MAX_LIMIT("0011","购物车最多添加{}商品"),
    OREDER_STATUS_NO_PAY("0012" , "{}订单，不能支付"),


    ;

    private String code;

    private String msg;

    ResultInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public ResultInfo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
