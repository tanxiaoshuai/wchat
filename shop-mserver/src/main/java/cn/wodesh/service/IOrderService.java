package cn.wodesh.service;


import java.util.Map;

/**
 * Created by TS on 2018/4/21.
 */
public interface IOrderService {

    public Object findByOrderId(String orderid) throws Exception;

    public Object addOrder(Map body)throws Exception;

}
