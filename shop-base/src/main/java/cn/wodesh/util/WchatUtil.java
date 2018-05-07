package cn.wodesh.util;

import cn.wodesh.config.WchatConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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

    /**
     * 金额换算
     * @param price
     * @return
     */
    public static String priceFormat(Integer price ,  Integer discount){
        Double d = price * discount / 100D / 100D;
        return String.format("%.2f" , d);
    }


    /**
     * 金额换算
     * @param price
     * @return
     */
    public static String priceFormat(Integer price){
        Double d = price  / 100D;
        return String.format("%.2f" , d);
    }

    /**
     * 金额换算
     * @param price
     * @return
     */
    public static String priceFormat(Long price){
        Double d = price  / 100D;
        return String.format("%.2f" , d);
    }

    /**
     * 金额换算
     * @param o
     * @return
     */
    public static String StrFormat(Object o){
        return String.format("%.2f" , o);
    }

    public static Integer CashFormatInt (String cash){
        return (int) (Double.parseDouble(cash) * 100);
    }

    public static String priceFormat(List<String> price){
        String p = null;
        if(price.size() > 0){
            HashSet set = new HashSet(price);
            price.clear();
            price.addAll(set);
        }
        if(price.size() == 1)
            p = price.get(0);
        if(price.size() > 1){
            StringBuffer prices = new StringBuffer();
            minAndMax(price);
            prices.append(price.get(0));
            prices.append("-");
            prices.append(price.get(1));
            p = prices.toString();
        }
        return p;
    }

    /**
     * 取出数组最值
     * @param price
     * @return
     */
    public static List<String> minAndMax(List<String> price){
        Double min,max;
        min=max= Double.parseDouble(price.get(0));
        for(int i=0;i<price.size();i++){
            Double s = Double.parseDouble(price.get(i));
            if(s>max)   // 判断最大值
                max=s;
            if(s<min)   // 判断最小值
                min=s;
        }
        price.clear();
        price.add(StrFormat(min));
        price.add(StrFormat(max));
        return price;
    }

    public static void main(String[] args) {
        System.out.println(CashFormatInt("68.12"));
    }
}
