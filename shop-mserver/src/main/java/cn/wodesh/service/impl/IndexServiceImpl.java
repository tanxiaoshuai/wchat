package cn.wodesh.service.impl;

import cn.wodesh.bean.IndexBanner;
import cn.wodesh.bean.ProductTpye;
import cn.wodesh.config.AppConfig;
import cn.wodesh.dao.IndexBannerDao;
import cn.wodesh.dao.IndexProductDao;
import cn.wodesh.dao.ProductTpyeDao;
import cn.wodesh.redis.RedisUtil;
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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Object findIndex() throws Exception {
        if(redisUtil.exists(AppConfig.INDEX_PAGE_STAYLE_KEY)){
            JSONObject object = JSONObject.parseObject((String) redisUtil.get(AppConfig.INDEX_PAGE_STAYLE_KEY));
            return ResultUtil.success(object);
        }
        JSONObject index = new JSONObject();
        index.put("banner" , indexBannerDao.findByList(IndexBanner.class));
        index.put("type" , productTpyeDao.findBySQLRequireToList(new StringBuffer().append("pt_status=")
                .append(1).toString() , ProductTpye.class));
        index.put("productList" , indexProductDao.findIndexProductList());
        redisUtil.set(AppConfig.INDEX_PAGE_STAYLE_KEY , index.toJSONString());
        return ResultUtil.success(index);
    }
}
