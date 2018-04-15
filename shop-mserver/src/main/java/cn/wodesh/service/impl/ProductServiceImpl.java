package cn.wodesh.service.impl;

import cn.wodesh.bean.Product;
import cn.wodesh.bean.ProductField;
import cn.wodesh.dao.ProductDao;
import cn.wodesh.dao.ProductFieldDao;
import cn.wodesh.dao.util.SqlKeyVal;
import cn.wodesh.service.IProductService;
import cn.wodesh.util.ResultUtil;
import cn.wodesh.util.WchatUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TS on 2018/4/14.
 */
@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductFieldDao productFieldDao;


    @Override
//    @Cacheable(key ="#p0")
    public Object findById(String proid) throws Exception {
        Product product = productDao.findById(proid , Product.class);
        product.setFieldList(productFieldDao.findBySQLRequireToList(SqlKeyVal.field("pa_proid" , proid) , ProductField.class));
        List<ProductField> list = product.getFieldList();
        if(list != null && list.size() == 0)
            return ResultUtil.success(product);
        product.setFieldList(null);
        JSONObject object = (JSONObject) JSONObject.toJSON(product);
        JSONObject attribute = new JSONObject();
        JSONArray value = new JSONArray();
        JSONArray relationList = new JSONArray();
        List<String> showprice = new ArrayList<>();
        List<String> showoldprice = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            ProductField pf = list.get(i);
            value.add(pf.getField());
            JSONObject ob = (JSONObject) JSONObject.toJSON(pf);
            Integer oldPrice = ob.getInteger("price");
            String price = WchatUtil.priceFormat( oldPrice , product.getDiscount());
            showoldprice.add(WchatUtil.priceFormat(oldPrice));
            showprice.add(price);
            ob.put("price" , price);
            ob.remove("proid");
            ob.remove("fieldkey");
            relationList.add(ob);
        }
        attribute.put("value" , value);
        attribute.put("key" , list.get(0).getFieldkey());
        object.put("attribute" , attribute);
        object.put("fieldList" , relationList);
        object.put("showprice" , WchatUtil.priceFormat(showprice));
        object.put("showoldprice" , WchatUtil.priceFormat(showoldprice));
        return ResultUtil.success(object);
    }
}
