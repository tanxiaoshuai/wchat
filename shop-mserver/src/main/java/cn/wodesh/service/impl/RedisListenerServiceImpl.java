package cn.wodesh.service.impl;

import cn.wodesh.bean.Order;
import cn.wodesh.config.AppConfig;
import cn.wodesh.dao.OrderDao;
import cn.wodesh.redis.IRedisListenerService;
import cn.wodesh.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisListenerServiceImpl implements IRedisListenerService{

    @Autowired
    private OrderDao orderDao;

    @Override
    public void redisOnMessage(String key) throws Exception {
        if(key.contains(AppConfig.ORDER_NO_PAY_KEY)){
            orderDao.updateBySQLRequire(new StringBuffer().append(" o_status= ").append(5)
                    .append(" where ").append(" o_payid= ").append("'").
                            append(KeyUtil.getKeyToId(key)).append("' and ").append(" o_status= ").append(1).toString() , Order.class);
        }
    }
}
