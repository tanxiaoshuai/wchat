package cn.wodesh.service;


/**
 * Created by TS on 2018/5/5.
 */
public interface IPayService {

    public Object pay(String orderid) throws Exception;

    public Object payCallback(String xml) throws Exception;
}
