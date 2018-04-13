package cn.wodesh.service.impl;

import cn.wodesh.bean.User;
import cn.wodesh.config.AppConfig;
import cn.wodesh.dao.UserDao;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.service.IUserService;
import cn.wodesh.util.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by TS on 2018/4/11.
 */

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Object login(String code , String token , String mac) throws Exception {
        ParamValidateUtil.notNull(mac , "手机mac地址不能为空");
        User user = null;
        if (RegexUtil.isNotNull(token))
            user = (User) redisUtil.get((String) TokenUtil.tokenParam(token).get(0));
        if (user != null && token != null && token.equals(user.getToken()))
            return ResultUtil.success(user);
        String openid = WchatUtil.getOpenId(code);
        String us = WchatUtil.getWchatUser(openid , TokenThread.access_token);
        user = JSONObject.parseObject(us , User.class);
        user.setUserid(KeyUtil.uuid());
        user.setMac(mac);
        User use = userDao.findBySQLRequireToBean("openid='"+openid+"'" , User.class);
        if(use == null){
            userDao.save(user);
        } else {
            use.setHeadimgurl(user.getHeadimgurl());
            use.setNickname(user.getNickname());
            use.setMac(mac);
            userDao.updateById(use);
            user = use;
        }
        user.setToken(TokenUtil.createToken(user.getUserid() , mac));
        redisUtil.set(user.getUserid() , user , AppConfig.REDIS_TOKEN_OUT_TIME);
        return ResultUtil.success(user);
    }
}
