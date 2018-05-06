package cn.wodesh.config;

public class WchatConfig {

    public static final String PAY_SHOP_NAME = "潮流设计小店";
    public static final String MCH_ID = "1438014202";
    public static final String PAY_KEY = "TggsTgyOPkljGHkHIDFFTggsTFlvbUds";
    public static final String APPID = "wxace6b87996d2fe13";
    public static final String APPSECRET = "747356f43caa06cac936c43571d16f76";
    public static final String WX_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
    public static final String WX_GET_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code={code}&grant_type=authorization_code";
    public static final String WX_GETUSER_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={token}&openid={openid}&lang=zh_CN&lang=zh_CN";
    public static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
