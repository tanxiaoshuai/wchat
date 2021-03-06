package cn.wodesh.service;

import cn.wodesh.bean.ShopCar;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/19.
 */
public interface IShopCarService {

    public Object findShopCarList( Integer page , Integer size) throws Exception;

    public Object changeNumber(List<ShopCar> shopCars) throws Exception;

    public Object save(Map map) throws Exception;

    public Object checkProduct(List<String> list) throws Exception;

    public Object checkProduct(Map map) throws Exception;

    public Object ShopCarToOderInfo(String out_trade_no) throws Exception;

    public Object deleteid(List<String> shopcarids) throws Exception;
}
