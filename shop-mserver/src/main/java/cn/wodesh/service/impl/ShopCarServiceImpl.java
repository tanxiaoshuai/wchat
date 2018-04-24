package cn.wodesh.service.impl;

import cn.wodesh.bean.ShopCar;
import cn.wodesh.config.StatusConfig;
import cn.wodesh.dao.ShopCarDao;
import cn.wodesh.service.IShopCarService;
import cn.wodesh.util.ParamValidateUtil;
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
}
