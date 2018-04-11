package cn.wodesh.config;

public class WchatConfig {

    public static String APPID = "wxace6b87996d2fe13";
    public static String APPSECRET = "747356f43caa06cac936c43571d16f76";
    public static final String WX_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
    public static final String WX_GET_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code={code}&grant_type=authorization_code";
    public static final String WX_GETUSER_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={token}&openid={openid}&lang=zh_CN&lang=zh_CN";
}
