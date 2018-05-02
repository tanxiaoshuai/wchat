package cn.wodesh.service.impl;

import cn.wodesh.bean.IndexBanner;
import cn.wodesh.bean.ProductTpye;
import cn.wodesh.config.AppConfig;
import cn.wodesh.dao.IndexBannerDao;
import cn.wodesh.dao.IndexProductDao;
import cn.wodesh.dao.ProductDao;
import cn.wodesh.dao.ProductTpyeDao;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.service.IIndexService;
import cn.wodesh.util.ParamValidateUtil;
import cn.wodesh.util.ResultUtil;
import cn.wodesh.util.WchatUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    private ProductDao productDao;

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

    @Override
    public Object findProductIndexCutpage(Map map) throws Exception {
        String size = map.get("size")+"";
        String page = map.get("page")+"";
        String type = map.get("typeid")+"";
        ParamValidateUtil.notNull(type , "板块分类类型不能为空");
        map.clear();
        map.put("ipid" , type);
        map.put("startpage" , (Long.parseLong(page) - 1) * Long.parseLong(size));
        map.put("size" , Integer.parseInt(size));
        List<Map> list = productDao.findProductListCutPage(map);
        for(Map m : list){
            Integer price = Integer.parseInt(m.get("showprice").toString());
            m.put("showprice" , WchatUtil.priceFormat(price , Integer.parseInt(m.get("discount").toString())));
            m.put("showoldprice" , WchatUtil.priceFormat(price));
            m.put("discount" , WchatUtil.priceFormat(Integer.parseInt(m.get("discount")+"")));
        }
        return ResultUtil.success(list);
    }
}
