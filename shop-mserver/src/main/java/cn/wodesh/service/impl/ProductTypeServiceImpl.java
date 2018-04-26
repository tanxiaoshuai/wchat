package cn.wodesh.service.impl;
import cn.wodesh.bean.ProductTpye;
import cn.wodesh.config.AppConfig;
import cn.wodesh.dao.ProductTpyeDao;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.service.IProductTypeService;
import cn.wodesh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TS on 2018/4/19.
 */
@Service
public class ProductTypeServiceImpl implements IProductTypeService {

    @Autowired
    private ProductTpyeDao productTpyeDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Object findByProductType() throws Exception {
        if(redisUtil.exists(AppConfig.PRODUCT_TYPE_LIST_KEY)){
            List<ProductTpye> productTpye = (List<ProductTpye>) redisUtil.get(AppConfig.PRODUCT_TYPE_LIST_KEY);
            return ResultUtil.success(productTpye);
        }
        List<ProductTpye> productTpye = productTpyeDao.findByProductTypeList(ProductTpye.class);
        redisUtil.set(AppConfig.PRODUCT_TYPE_LIST_KEY , productTpye);
        return ResultUtil.success(productTpye);
    }
}
