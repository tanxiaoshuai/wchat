package cn.wodesh.service.impl;

import cn.wodesh.bean.Address;
import cn.wodesh.bean.ShopCar;
import cn.wodesh.config.AppConfig;
import cn.wodesh.config.StatusConfig;
import cn.wodesh.dao.AddressDao;
import cn.wodesh.dao.ShopCarDao;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.service.IShopCarService;
import cn.wodesh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/19.
 */
@Service
public class ShopCarServiceImpl implements IShopCarService{

    @Autowired
    private ShopCarDao shopCarDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Object findShopCarList(Integer page, Integer size) throws Exception {
        ParamValidateUtil.notNull(page , "页码不能为空");
        ParamValidateUtil.notNull(size , "分页size不能为空");
        List<ShopCar> list = shopCarDao.
                findShopCarList(TokenUtil.tokenGetUser().getUserid() , (page - 1) * size , size);
        for(ShopCar s : list)
            s.shopCatFormat(s);
        return ResultUtil.success(list);
    }

    @Override
    @Transactional
    public Object changeNumber(List<ShopCar> shopCars) throws Exception {
        for(ShopCar s : shopCars)
            shopCarDao.updateById(s);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Object save(Map map) throws Exception {
        map.put("userid" , TokenUtil.tokenGetUser().getUserid());
        Map m = shopCarDao.findShopCarProductNumberOrshopCarId(map);
        Integer num =  map.get("number") != null && !"".equals(map.get("number").toString())  ? Integer.parseInt(map.get("number")+"") : null;
        ParamValidateUtil.notNull(num , "数量不能为空");
        ParamValidateUtil.IntegrCheck(num , 99 ,"数量不能超过99");
        if(m == null){
            map.put("shopcarid" , KeyUtil.uuid());
            shopCarDao.add(map);
            return ResultUtil.success();
        }
        int number_ = Integer.parseInt(map.get("number")+"");
        int number = Integer.parseInt(m.get("number")+"");
        if(99 - number < number_)
            map.put("number" , 99 - number);
        map.put("shopcarid" , m.get("shopcarid"));
        shopCarDao.updateNumber(map);
        return ResultUtil.success();
    }

    @Override
    public Object checkProduct(List<String> list) throws Exception {
        List<ShopCar> shopCars = new ArrayList<>();
        Long cashCount = 0L;
        Map<String , Object> map = new HashMap<>();
            for(String shopcarid : list){
            ShopCar shopCar = shopCarDao.findShopCarBean(shopcarid);
                System.out.println(shopCar);
            cashCount += shopCar.getNumber()
                    * Long.parseLong(shopCar.getDiscount())
                    * Long.parseLong(shopCar.getPrice()) / 100L + Long.parseLong(shopCar.getFreight());
            shopCar.shopCatFormat(shopCar);
            shopCars.add(shopCar);
        }
        Address address = addressDao.findUserToDefualtAddress(TokenUtil.tokenGetUser().getUserid() , StatusConfig.ADDRES_DEFUALT);
        String out_trade_no = KeyUtil.uuid();
        map.put("products" , shopCars);
        map.put("address" , address);
        map.put("cashCount" , WchatUtil.priceFormat(cashCount));
        map.put("out_trade_no" , out_trade_no);
        redisUtil.set(KeyUtil.outTradeNoKey(out_trade_no) , map , AppConfig.REDIS_SHOPCAR_BY_ORDER_OUT_TIME);
        map.clear();
        map.put("out_trade_no" , out_trade_no);
        return ResultUtil.success(map);
    }

    @Override
    public Object ShopCarToOderInfo(String out_trade_no) throws Exception {
        Map map = (Map) redisUtil.get(KeyUtil.outTradeNoKey(out_trade_no));
        redisUtil.setExpire(out_trade_no , AppConfig.REDIS_SHOPCAR_BY_ORDER_OUT_TIME);
        return ResultUtil.success(map);
    }

    @Override
    @Transactional
    public Object deleteid(List<String> shopcarids) throws Exception {
        for(String s: shopcarids)
            shopCarDao.deleteById(s , ShopCar.class);
        return ResultUtil.success();
    }
}
