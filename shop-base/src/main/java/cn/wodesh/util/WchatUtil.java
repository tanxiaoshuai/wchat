package cn.wodesh.util;

import cn.wodesh.config.WchatConfig;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TS on 2018/4/11.
 */
public class WchatUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(WchatUtil.class);

    /**
     * 获取 openID
      * @param code
     * @return
     * @throws Exception
     */
    public static String getOpenId(String code) throws Exception{
        String url = WchatConfig.WX_GET_OPENID_URL.replace("{code}" , code);
        String result = HttpClientUtil.getHttps(url , null);
        LOGGER.info("获取openid返回参数：" + result);
        JSONObject res = JSONObject.parseObject(result);
        return res.getString("openid");
    }

    /**
     * 获取用户信息
     * @param openid
     * @param token
     * @return
     * @throws Exception
     */
    public static String getWchatUser(String openid , String token) throws Exception {
        String url = WchatConfig.WX_GETUSER_URL.replace("{token}" , token)
                .replace("{openid}" , openid);
        String result = HttpClientUtil.getHttps(url , null);
        LOGGER.info("获取微信用户信息：" + result);
        JSONObject user = JSONObject.parseObject(result);
        JSONObject us = new JSONObject();
        us.put("openid" , user.getString("openid"));
        us.put("nickname" , user.getString("nickname"));
        us.put("headimgurl" , user.getString("headimgurl"));
        us.put("city" , user.getString("city"));
        us.put("province" , user.getString("province"));
        us.put("country" , user.getString("country"));
        int sex = user.getInteger("sex");
        if (sex == 1)
            us.put("sex" , "男");
        if (sex == 2)
            us.put("sex" , "女");
        if (sex == 0)
            us.put("sex" , "未知");
        user.clear();
        return us.toString();
    }

    /**
     * 获取微信token
     * @return
     * @throws Exception
     */
    public static String getToken() throws Exception {
        String result = HttpClientUtil.getHttps(WchatConfig.WX_TOKEN_URL , null);
        LOGGER.info("获取微信token返回数据：" + result);
        JSONObject object = JSONObject.parseObject(result);
        return object.getString("access_token");
    }

    public static void main(String[] args) {
        System.out.println("owF-Kw_dNmnrDON7ZGz8VDP3p7k4".length());
    }
}
