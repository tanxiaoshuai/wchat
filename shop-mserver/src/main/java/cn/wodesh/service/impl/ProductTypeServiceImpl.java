package cn.wodesh.service.impl;
import cn.wodesh.bean.ProductTpye;
import cn.wodesh.dao.ProductTpyeDao;
import cn.wodesh.service.IProductTypeService;
import cn.wodesh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TS on 2018/4/19.
 */
@Service
public class ProductTypeServiceImpl implements IProductTypeService {

    @Autowired
    private ProductTpyeDao productTpyeDao;

    @Override
    public Object findByProductType() throws Exception {
        return ResultUtil.
                success(productTpyeDao.findByProductTypeList(ProductTpye.class));
    }
}
