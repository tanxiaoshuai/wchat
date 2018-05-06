package cn.wodesh.service;

import java.util.Map;

/**
 * Created by TS on 2018/5/5.
 */
public interface IPayService {

    public Object pay(Map map) throws Exception;

    public Object payCallback(String xml) throws Exception;
}
