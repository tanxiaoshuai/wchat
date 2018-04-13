package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by TS on 2018/4/12.
 */
@Component
@Scope("prototype")
@TableName(name = "t_product")
public class Product {

    @ID(increment = false)
    @Column(name = "p_id")
    private String proid;
    @Column(name = "p_name")
    private String proname;
    @Column(name = "p_describe")
    private String prodescribe;
    @Column(name = "p_imgs")
    private String imgs;
    @Column(name = "p_price")
    private Integer price;
    @Column(name = "p_createtime")
    private String createtime;
    @Column(name = "p_status")
    private Integer prostatus;
    @Column(name = "p_type")
    private Integer protype;
    @Column(name = "p_discount")
    private Integer discount;
    @Column
    private String extend;

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getProdescribe() {
        return prodescribe;
    }

    public void setProdescribe(String prodescribe) {
        this.prodescribe = prodescribe;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getProstatus() {
        return prostatus;
    }

    public void setProstatus(Integer prostatus) {
        this.prostatus = prostatus;
    }

    public Integer getProtype() {
        return protype;
    }

    public void setProtype(Integer protype) {
        this.protype = protype;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"proid\":\"")
                .append(proid).append('\"');
        sb.append(",\"proname\":\"")
                .append(proname).append('\"');
        sb.append(",\"prodescribe\":\"")
                .append(prodescribe).append('\"');
        sb.append(",\"imgs\":\"")
                .append(imgs).append('\"');
        sb.append(",\"price\":")
                .append(price);
        sb.append(",\"createtime\":\"")
                .append(createtime).append('\"');
        sb.append(",\"prostatus\":")
                .append(prostatus);
        sb.append(",\"protype\":")
                .append(protype);
        sb.append(",\"discount\":")
                .append(discount);
        sb.append(",\"extend\":\"")
                .append(extend).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
