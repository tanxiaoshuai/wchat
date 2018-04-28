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
    ANOTHERdDEVICELOGIN("0005" , "用户在其他设备上登录"),
    USER_ISNULL("0006" , "用户不存在"),
    PHONE_ISNOTNULL("0007" , "手机号已注册"),
    PASSWORDERROR("0008" , "密码错误"),
    TOKENCHECKERROR("0009" , "认证失败"),
    NICKNAME_ISNOTNULL("0010" , "用户名已存在"),
    AL_SMS_MESSAGE_ERROR("0011" , "短信验证码获取失败"),
    UPDATE_PHONE_ISTHIS("0012" , "修改的手机号不能与当前手机号相同"),

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
