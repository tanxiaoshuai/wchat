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
    public Object login(String code , String token) throws Exception {
        User user = null;
        if (RegexUtil.isNotNull(token))
            user = (User) redisUtil.get((String) TokenUtil.tokenParam(token).get(0));
        if (user != null && token != null && token.equals(user.getToken()))
            return ResultUtil.success(user);
        String openid = WchatUtil.getOpenId(code);
        String us = WchatUtil.getWchatUser(openid , TokenThread.access_token);
        user = JSONObject.parseObject(us , User.class);
        user.setUserid(KeyUtil.uuid());
        User use = userDao.findBySQLRequireToBean("openid='"+openid+"'" , User.class);
        if(use == null){
            userDao.save(user);
        } else {
            use.setHeadimgurl(user.getHeadimgurl());
            use.setNickname(user.getNickname());
            userDao.updateById(use);
            user = use;
        }
        user.setToken(TokenUtil.createToken(user.getUserid()));
        redisUtil.set(KeyUtil.tokenKey(user.getUserid()) , user , AppConfig.REDIS_TOKEN_OUT_TIME);
        return ResultUtil.success(user);
    }

    @Override
    public Object loginTest(String code, String token) throws Exception {
        User user = null;


        if (RegexUtil.isNotNull(token))
            user = (User) redisUtil.get((String) TokenUtil.tokenParam(token).get(0));
        if (user != null && token != null && token.equals(user.getToken()))
            return ResultUtil.success(user);
        user = userDao.findBySQLRequireToBean("openid='owF-Kw_dNmnrDON7ZGz8VDP3p7k4'" , User.class);
        user.setToken(TokenUtil.createToken(user.getUserid()));
        redisUtil.set(KeyUtil.tokenKey(user.getUserid()) , user , AppConfig.REDIS_TOKEN_OUT_TIME);
        return ResultUtil.success(user);
    }
}
