package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by TS on 2018/4/14.
 */
@Component
@Scope("prototype")
@TableName(name = "t_product_field")
public class ProductField {

    @ID(increment = false)
    @Column(name = "pa_id")
    private String fieldid;
    @Column(name = "pa_proid")
    private String proid;
    @Column(name = "pa_field_key")
    private String fieldkey;
    @Column(name = "pa_field")
    private String field;
    @Column(name = "pa_spec_key")
    private String speckey;
    @Column(name = "pa_spec")
    private String spec;
    @Column(name = "pa_price")
    private String price;
    @Column(name = "pa_stock")
    private Integer stock;
    @Column(name = "pa_img")
    private String img;
    @Column(name = "pa_extend")
    private String extend;

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getFieldkey() {
        return fieldkey;
    }

    public void setFieldkey(String fieldkey) {
        this.fieldkey = fieldkey;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSpeckey() {
        return speckey;
    }

    public void setSpeckey(String speckey) {
        this.speckey = speckey;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        sb.append("\"fieldid\":\"")
                .append(fieldid).append('\"');
        sb.append(",\"proid\":\"")
                .append(proid).append('\"');
        sb.append(",\"fieldkey\":\"")
                .append(fieldkey).append('\"');
        sb.append(",\"field\":\"")
                .append(field).append('\"');
        sb.append(",\"speckey\":\"")
                .append(speckey).append('\"');
        sb.append(",\"spec\":\"")
                .append(spec).append('\"');
        sb.append(",\"price\":")
                .append(price);
        sb.append(",\"stock\":")
                .append(stock);
        sb.append(",\"img\":\"")
                .append(img).append('\"');
        sb.append(",\"extend\":\"")
                .append(extend).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
