package cn.wodesh.service.impl;

import cn.wodesh.bean.Address;
import cn.wodesh.bean.ShopCar;
import cn.wodesh.config.ResultInfo;
import cn.wodesh.config.StatusConfig;
import cn.wodesh.dao.AddressDao;
import cn.wodesh.dao.ProductFieldDao;
import cn.wodesh.dao.ShopCarDao;
import cn.wodesh.exception.FinalException;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.service.IShopCarService;
import cn.wodesh.util.*;
import com.alibaba.fastjson.JSONObject;
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
    private ProductFieldDao fieldDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Object findShopCarList(String userid, Integer page, Integer size) throws Exception {
        ParamValidateUtil.notNull(userid , "用户id不能为空");
        ParamValidateUtil.notNull(page , "页码不能为空");
        ParamValidateUtil.notNull(size , "分页size不能为空");
        List<ShopCar> list = shopCarDao.
                findShopCarList(userid , (page - 1) * size , size);
        for(ShopCar s : list)
            s.shopCatFormat(s);
        return ResultUtil.success(list);
    }

    @Override
    public Object changeNumber(Integer number, String userid , String fieldid) throws Exception {
        shopCarDao.changeNumber(number , userid , fieldid);
        return ResultUtil.success();
    }

    @Override
    public Object save(Map map) throws Exception {
        map.put("userid" , TokenUtil.
                tokenForUserId(RequestUtil.getHeader("token")));
        Integer number = shopCarDao.findShopCarProductNumber(map);
        if(number == null){
            shopCarDao.add(map);
            return ResultUtil.success();
        }
        int number_ = Integer.parseInt(map.get("number")+"");
        if(99 - number < number_)
            map.put("number" , 99 - number);
        shopCarDao.updateNumber(map);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Object checkProduct(List<String> list) throws Exception {
        List<ShopCar> shopCars = new ArrayList<>();
        Long cashCount = 0L;
        Map<String , Object> map = new HashMap<>();
            for(String fieldid : list){
            ShopCar shopCar = shopCarDao.findShopCarBean(fieldid);
            int a = fieldDao.updateStock(fieldid ,
                    shopCar.getNumber());
            if(a == 0){
                throw new FinalException(ResultInfo.SHOPCAR_CHOICE_ERROR);
            }
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
        redisUtil.set(KeyUtil.outTradeNoKey(out_trade_no) , map);
        return ResultUtil.success(map);
    }
}
