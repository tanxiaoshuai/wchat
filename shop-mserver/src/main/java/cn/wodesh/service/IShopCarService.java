package cn.wodesh.service;

import java.util.Map;

/**
 * Created by TS on 2018/4/19.
 */
public interface IShopCarService {

    public Object findShopCarList(String userid , Integer page , Integer size) throws Exception;

    public Object changeNumber(Integer number, String userid , String fieldid) throws Exception;

    public Object save(Map map) throws Exception;
}
