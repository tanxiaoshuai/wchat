package cn.wodesh.service.impl;

import cn.wodesh.bean.Order;
import cn.wodesh.dao.OrderDao;
import cn.wodesh.service.IOrderService;
import cn.wodesh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TS on 2018/4/21.
 */
@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderDao orderDao;

    @Override
    public Object findByOrderId(String orderid) throws Exception {
        Order order = orderDao.findByOrderId(orderid);
        order.orderFormat(order);
        return ResultUtil.success(order);
    }
}
