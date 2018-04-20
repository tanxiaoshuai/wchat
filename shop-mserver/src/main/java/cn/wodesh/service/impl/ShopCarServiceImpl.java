package cn.wodesh.service.impl;

import cn.wodesh.bean.ShopCar;
import cn.wodesh.config.StatusConfig;
import cn.wodesh.dao.ShopCarDao;
import cn.wodesh.service.IShopCarService;
import cn.wodesh.util.ResultUtil;
import cn.wodesh.util.WchatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TS on 2018/4/19.
 */
@Service
public class ShopCarServiceImpl implements IShopCarService{

    @Autowired
    private ShopCarDao shopCarDao;

    @Override
    public Object findShopCarList(String userid, Integer page, Integer size) throws Exception {
        List<ShopCar> list = shopCarDao.
                findShopCarList(userid , (page - 1) * size , size);
        for(ShopCar s : list){
            s.setPrice(WchatUtil.priceFormat(
                    Integer.parseInt(s.getPrice()) , Integer.parseInt(s.getDiscount())));
            s.setDiscount(WchatUtil.priceFormat(Integer.parseInt(s.getDiscount())));
            s.setFreight(WchatUtil.priceFormat(Integer.parseInt(s.getFreight())));
                if(s.getProstatus() == 1 && s.getStock() == 0) {
                    s.setProstatus(3);
                    s.setStatusinfo(StatusConfig.PRODUCTSTATUS.get(s.getProstatus()));
                }
                else
                    s.setStatusinfo(StatusConfig.PRODUCTSTATUS.get(s.getProstatus()));
        }
        return ResultUtil.success(list);
    }

    @Override
    public Object changeNumber(Integer number, String userid , String fieldid) throws Exception {
        shopCarDao.changeNumber(number , userid , fieldid);
        return ResultUtil.success();
    }
}
