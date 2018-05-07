package cn.wodesh.util;
import cn.wodesh.bean.User;
import cn.wodesh.config.AppConfig;
import cn.wodesh.config.ResultInfo;
import cn.wodesh.exception.FinalException;
import cn.wodesh.redis.RedisUtil;
import com.alibaba.fastjson.JSONArray;
import org.springframework.util.Base64Utils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TS on 2018/2/28.
 */
public class TokenUtil {

    /**
     * 创建token
     * @param userid
     * @return
     */
    public static String createToken(String userid){
        StringBuffer s = new StringBuffer();
        s.append(KeyUtil.tokenKey(userid)).append("&");
        s.append(System.currentTimeMillis());
        return Base64Utils.encodeToString(s.toString().getBytes());
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static List tokenParam(String token){
        if(RegexUtil.isNull(token))
            throw new FinalException(ResultInfo.NOAUTHORIZE);
        List list = new ArrayList();
        token = new String(Base64Utils.decodeFromString(token));
        Object [] objects = token.split("&");
        for(Object o : objects)
            list.add(o);
        return list;
    }

    public static String tokenForUserId(String token){
        String s = (String) tokenParam(token).get(0);
        String arr[] = s.split("_");
        return arr[arr.length - 1];
    }

    public static boolean checkToken(String token){
        List list = tokenParam(token);
        RedisUtil redisUtil = BeanFactoryUtil.getBeanByClass(RedisUtil.class);
        if(!redisUtil.exists((String) list.get(0)))
            throw new FinalException(ResultInfo.LOGINOUTTIME);
        User user = (User) redisUtil.get((String) list.get(0));
        if(!user.getToken().equals(token)){
                throw new FinalException(ResultInfo.TOKENCHECKERROR);
        }
        redisUtil.setExpire((String) list.get(0), AppConfig.REDIS_TOKEN_OUT_TIME);
        return true;
    }

    public static User tokenGetUser(){
        String token =RequestUtil.getHeader("token");
        if(RegexUtil.isNull(token))
            throw new FinalException(ResultInfo.NOAUTHORIZE);
        String userid = (String) tokenParam(token).get(0);
        RedisUtil redisUtil = BeanFactoryUtil.getBeanByClass(RedisUtil.class);
        return (User) redisUtil.get(userid);
    }

}
