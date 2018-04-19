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
import java.util.Map;
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
    @Cacheable(key ="#p0")
    public Object findById(String proid) throws Exception {
        Product product = productDao.findById(proid , Product.class);
        product.setFieldList(productFieldDao.findBySQLRequireToList(SqlKeyVal.field("pa_proid" , proid) , ProductField.class));
        List<ProductField> list = product.getFieldList();
        if(list != null && list.size() == 0)
            return ResultUtil.success(product);
        product.setFieldList(null);
        JSONObject object = (JSONObject) JSONObject.toJSON(product);
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
            String field = ob.getString("field");
            String fieldkey = ob.getString("fieldkey");
            String img = ob.getString("img");
            ob.remove("field");
            ob.remove("fieldkey");
            ob.remove("img");
            JSONObject o = null;
            if(relationList != null && relationList.size() > 0){
                int count = 0;
                for(int c = 0; c < relationList.size() ; c++){
                    JSONObject obj = relationList.getJSONObject(c);
                    if(field.equals(obj.getString("field"))){
                        obj.getJSONArray("speclist" ).add(ob);
                        count++;
                        break;
                    }
                }
                if(count == 0){
                    o = new JSONObject();
                    JSONArray arr = new JSONArray();
                    o.put("field" , field);
                    o.put("fieldkey" , fieldkey);
                    o.put("img" , img);
                    arr.add(ob);
                    o.put("speclist" , arr);
                    relationList.add(o);
                }
            }else {
                o = new JSONObject();
                JSONArray arr = new JSONArray();
                o.put("field" , field);
                o.put("fieldkey" , fieldkey);
                o.put("img" , img);
                arr.add(ob);
                o.put("speclist" , arr);
                relationList.add(o);
            }
        }
        object.put("fieldList" , relationList);
        object.put("showprice" , WchatUtil.priceFormat(showprice));
        object.put("showoldprice" , WchatUtil.priceFormat(showoldprice));
        return ResultUtil.success(object);
    }

    @Override
//    @Cacheable(key="#root.targetClass + #root.methodName")
    public Object findByCutProduct(Map condition) throws Exception {
        Long startpage = (Long.parseLong(condition.get("page").toString()) - 1 ) * Long.parseLong(condition.get("size").toString());
        condition.put("startpage" , startpage);
        List<Map> list = productDao.findByCutProduct(condition);
        System.out.println(JSONArray.toJSONString(list));
        for(Map m : list){
            Integer price = Integer.parseInt(m.get("showprice").toString());
            m.put("showprice" , WchatUtil.priceFormat(price , Integer.parseInt(m.get("discount").toString())));
            m.put("showoldprice" , WchatUtil.priceFormat(price));
        }
        return ResultUtil.success(list);
    }
}
