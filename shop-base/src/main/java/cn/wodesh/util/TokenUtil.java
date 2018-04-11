package cn.wodesh.util;


import cn.wodesh.config.ResultInfo;
import cn.wodesh.exception.FinalException;
import cn.wodesh.redis.RedisUtil;
import com.alibaba.fastjson.JSONArray;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TS on 2018/2/28.
 */
public class TokenUtil {

    /**
     * 创建token
     * @param userid
     * @param mac
     * @return
     */
    public static String createToken(String userid , String mac){
        StringBuffer s = new StringBuffer();
        s.append(userid).append("&");
        s.append(System.currentTimeMillis());
        s.append("&").append(mac);
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

    public static boolean checkToken(String token){
//        List list = tokenParam(token);
//        RedisUtil redisUtil = BeanFactoryUtil.getBeanByClass(RedisUtil.class);
//        redisUtil.setExpire((String) list.get(0), AppConfig.REDIS_TOKEN_OUT_TIME);
//        if(!redisUtil.exists((String) list.get(0)))
//            throw new FinalException(ResultInfo.LOGINOUTTIME);
//        UserBean user = (UserBean) redisUtil.get((String) list.get(0));
//        if(!user.getToken().equals(token)){
//            if (!user.getDeviceId().equals(list.get(2)))
//                throw new FinalException(ResultInfo.ANOTHERdDEVICELOGIN);
//            else
//                throw new FinalException(ResultInfo.TOKENCHECKERROR);
//        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(JSONArray.toJSON(tokenParam("5Zu05q60JjE1MjM0NTY4NjEzNzkmNzc=")));
    }


}
