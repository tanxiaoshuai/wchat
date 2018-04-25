package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import cn.wodesh.util.WchatUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/25.
 */
@Component
@Scope("prototype")
@TableName(name = "t_index_product")
public class IndexProduct {

    @ID(increment = false)
    @Column(name = "ip_id")
    private String id;

    @Column(name = "ip_title")
    private String title;

    private List<Map> productList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Map> getProductList() {
        return productList;
    }

    public void setProductList(List<Map> productList) {
        productMapFormat(productList);
        this.productList = productList;
    }

    public void productMapFormat(List<Map> list){
        for(Map m : list){
            Integer price = Integer.parseInt(m.get("showprice").toString());
            m.put("showprice" , WchatUtil.priceFormat(price , Integer.parseInt(m.get("discount").toString())));
            m.put("showoldprice" , WchatUtil.priceFormat(price));
            m.put("discount" , WchatUtil.priceFormat(Integer.parseInt(m.get("discount")+"")));
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"productList\":")
                .append(productList);
        sb.append('}');
        return sb.toString();
    }
}
