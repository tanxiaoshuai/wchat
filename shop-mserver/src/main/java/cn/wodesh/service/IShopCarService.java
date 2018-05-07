package cn.wodesh.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/19.
 */
public interface IShopCarService {

    public Object findShopCarList( Integer page , Integer size) throws Exception;

    public Object changeNumber(Integer number, String userid , String fieldid) throws Exception;

    public Object save(Map map) throws Exception;

    public Object checkProduct(List<String> list) throws Exception;
}
