package cn.wodesh.service.impl;
import cn.wodesh.bean.Product;
import cn.wodesh.bean.ProductField;
import cn.wodesh.dao.ProductDao;
import cn.wodesh.dao.ProductFieldDao;
import cn.wodesh.dao.util.SqlKeyVal;
import cn.wodesh.rabbitmq.RabbitMqSender;
import cn.wodesh.service.IProductService;
import cn.wodesh.util.RegexUtil;
import cn.wodesh.util.ResultUtil;
import cn.wodesh.util.TokenUtil;
import cn.wodesh.util.WchatUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private RabbitMqSender rabbitMqSender;


    @Override
    @Cacheable(key ="#p0")
    public Object findById(String proid) throws Exception {
        Product product = productDao.findById(proid , Product.class);
        Integer discount = Integer.parseInt(product.getDiscount());
        product.productFormat(product);
        product.setFieldList(productFieldDao.findBySQLRequireToList(SqlKeyVal.field("pa_proid" , proid) , ProductField.class));
        List<ProductField> list = product.getFieldList();
        if(list != null && list.size() == 0)
            return ResultUtil.success(product);
        product.setFieldList(null);
        JSONObject object = (JSONObject) JSONObject.toJSON(product);
        JSONArray relationList = new JSONArray();
        List<String> showprice = new ArrayList<>();
        List<String> showoldprice = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            ProductField pf = list.get(i);
            JSONObject ob = (JSONObject) JSONObject.toJSON(pf);
            Integer oldPrice = ob.getInteger("price");
            String price = WchatUtil.priceFormat( oldPrice , discount);
            showoldprice.add(WchatUtil.priceFormat(oldPrice));
            showprice.add(price);
            ob.put("price" , price);
            ob.remove("proid");
            String field = ob.getString("field");
            String fieldkey = ob.getString("fieldkey");
            String img = ob.getString("img");
            ob.put("choice" , ob.getInteger("stock") == 0 ? false : true);
            ob.remove("field");
            ob.remove("fieldkey");
            ob.remove("img");
            JSONObject o = null;
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
                o.put("field" , field);
                o.put("fieldkey" , fieldkey);
                o.put("img" , img);
                JSONArray arr =  new JSONArray();
                arr.add(ob);
                o.put("speclist" , arr);
                relationList.add(o);
            }
        }

        object.put("fieldList" , relationList);
        object.put("showprice" , WchatUtil.priceFormat(showprice));
        object.put("showoldprice" , WchatUtil.priceFormat(showoldprice));
        rabbitMqSender.send_product_clicks(proid);
        return ResultUtil.success(object);
    }

    @Override
    public Object findByCutProduct(Map condition, HttpServletRequest request) throws Exception {
        Long startpage = (Long.parseLong(condition.get("page").toString()) - 1 ) * Long.parseLong(condition.get("size").toString());
        condition.put("startpage" , startpage);
        String keywords = (String) condition.get("keywords");
        if(RegexUtil.isNotNull(keywords)){
            rabbitMqSender.send(new JSONObject().
                    fluentPut("userid" , TokenUtil.
                            tokenForUserId(request.getHeader("token"))).
                    fluentPut("keyname" , keywords));
        }
        List<Map> list = productDao.findByCutProduct(condition);
        for(Map m : list){
            Integer price = Integer.parseInt(m.get("showprice").toString());
            m.put("showprice" , WchatUtil.priceFormat(price , Integer.parseInt(m.get("discount").toString())));
            m.put("showoldprice" , WchatUtil.priceFormat(price));
            m.put("discount" , WchatUtil.priceFormat(Integer.parseInt(m.get("discount")+"")));
        }
        return ResultUtil.success(list);
    }

    @Override
    public void updateClicks(String proid) throws Exception {
        productDao.updateBySQLRequire(new StringBuffer().append("p_clicks=").
                append("p_clicks + 1 where p_id='").append(proid).
                append("'").toString() , Product.class);
    }

}
