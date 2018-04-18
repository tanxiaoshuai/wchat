package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Column(name = "p_createtime")
    private String createtime;
    @Column(name = "p_keywords")
    private String keywords;
    @Column(name = "p_status")
    private Integer prostatus;
    @Column(name = "p_freight")
    private Integer freight;
    @Column(name = "p_type")
    private String protype;
    @Column(name = "p_typechild")
    private String protypechild;
    @Column(name = "p_discount")
    private Integer discount;
    @Column(name = "p_clicks")
    private Long clicks;
    @Column(name = "p_remarks")
    private String remarks;
    @Column(name = "p_solds")
    private Long solds;
    @Column(name = "p_info")
    private String info;
    @Column(name = "p_extend")
    private String extend;

    private List<ProductField> fieldList;

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public String getProtypechild() {
        return protypechild;
    }

    public void setProtypechild(String protypechild) {
        this.protypechild = protypechild;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public List<ProductField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<ProductField> fieldList) {
        this.fieldList = fieldList;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getSolds() {
        return solds;
    }

    public void setSolds(Long solds) {
        this.solds = solds;
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
        sb.append(",\"createtime\":\"")
                .append(createtime).append('\"');
        sb.append(",\"keywords\":\"")
                .append(keywords).append('\"');
        sb.append(",\"prostatus\":")
                .append(prostatus);
        sb.append(",\"freight\":")
                .append(freight);
        sb.append(",\"protype\":\"")
                .append(protype).append('\"');
        sb.append(",\"protypechild\":\"")
                .append(protypechild).append('\"');
        sb.append(",\"discount\":")
                .append(discount);
        sb.append(",\"clicks\":")
                .append(clicks);
        sb.append(",\"remarks\":\"")
                .append(remarks).append('\"');
        sb.append(",\"solds\":")
                .append(solds);
        sb.append(",\"info\":\"")
                .append(info).append('\"');
        sb.append(",\"extend\":\"")
                .append(extend).append('\"');
        sb.append(",\"fieldList\":")
                .append(fieldList);
        sb.append('}');
        return sb.toString();
    }
}
