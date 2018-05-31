package cn.wodesh.service.impl;

import cn.wodesh.bean.Order;
import cn.wodesh.bean.User;
import cn.wodesh.config.AppConfig;
import cn.wodesh.dao.OrderDao;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.service.IPayService;
import cn.wodesh.util.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by TS on 2018/5/5.
 */
@Service
public class PayServiceImpl implements IPayService{

    @Autowired
    private PayUtil payUtil;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public Object pay(String orderid) throws Exception {
        String uuid = KeyUtil.uuid();
        String payid = KeyUtil.orderNoPayKey(uuid);
        Order o = new Order();
        o.setOrderid(orderid);
        o.setPayid(uuid);
        orderDao.updateById(o);
        Order order = orderDao.findByOrderId(orderid);
        User user = TokenUtil.tokenGetUser();
        String res = payUtil.Pay(user.getOpenid() ,
                WchatUtil.CashFormatInt(order.getPaycash())+"" , uuid , "1");
        redisUtil.set(payid , null , AppConfig.ORDER_NO_PAY_OUT_TIME);
        return ResultUtil.success(JSONObject.parseObject(res));
    }

    @Override
    public Object payCallback(String xml) throws Exception {
        return payUtil.callback(xml);
    }
}
