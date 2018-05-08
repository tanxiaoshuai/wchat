package cn.wodesh.service.impl;

import cn.wodesh.bean.Order;
import cn.wodesh.config.AppConfig;
import cn.wodesh.dao.OrderDao;
import cn.wodesh.dao.ProductFieldDao;
import cn.wodesh.redis.IRedisListenerService;
import cn.wodesh.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RedisListenerServiceImpl implements IRedisListenerService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductFieldDao productFieldDao;

    @Override
    @Transactional
    public void redisOnMessage(String key) throws Exception {
        if(key.contains(AppConfig.ORDER_NO_PAY_KEY)){
            String payid =KeyUtil.getKeyToId(key);
            List<Map> list = orderDao.findByOrderIdAndNumber(payid , 1);
            for(Map m : list)
                productFieldDao.updateOrderToNumber(Integer.parseInt(m.get("number").toString()) ,
                        m.get("fieldid").toString());
            orderDao.updateBySQLRequire(new StringBuffer().append(" o_status= ").append(5)
                    .append(" where ").append(" o_payid= ").append("'").
                            append(payid).append("' and ").append(" o_status= ").append(1).toString() , Order.class);
        }
    }
}
