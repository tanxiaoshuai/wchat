package cn.wodesh.service.impl;

import cn.wodesh.bean.IndexBanner;
import cn.wodesh.bean.ProductTpye;
import cn.wodesh.dao.IndexBannerDao;
import cn.wodesh.dao.IndexProductDao;
import cn.wodesh.dao.ProductTpyeDao;
import cn.wodesh.service.IIndexService;
import cn.wodesh.util.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TS on 2018/4/25.
 */
@Service
public class IndexServiceImpl implements IIndexService{

    @Autowired
    private IndexProductDao indexProductDao;

    @Autowired
    private IndexBannerDao indexBannerDao;

    @Autowired
    private ProductTpyeDao productTpyeDao;

    @Override
    public Object findIndex() throws Exception {
        JSONObject index = new JSONObject();
        index.put("banner" , indexBannerDao.findByList(IndexBanner.class));
        index.put("type" , productTpyeDao.findBySQLRequireToList(new StringBuffer().append("pt_status=")
                .append(1).toString() , ProductTpye.class));
        index.put("productList" , indexProductDao.findIndexProductList());
        return ResultUtil.success(index);
    }
}
