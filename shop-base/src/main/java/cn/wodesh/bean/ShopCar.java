package cn.wodesh.bean;

import cn.wodesh.config.StatusConfig;
import cn.wodesh.util.WchatUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TS on 2018/4/19.
 */
@Component
@Scope("prototype")
public class ShopCar {

    private String proid;

    private String fieldid;

    private String prodescribe;

    private String price;

    private Integer prostatus;

    private String freight;

    private String discount;

    private String profield;

    private String spec;

    private Integer stock;

    private String statusinfo;

    private Integer number;

    private String keyfrom;

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getProdescribe() {
        return prodescribe;
    }

    public void setProdescribe(String prodescribe) {
        this.prodescribe = prodescribe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getProstatus() {
        return prostatus;
    }

    public void setProstatus(Integer prostatus) {
        this.prostatus = prostatus;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getProfield() {
        return profield;
    }

    public void setProfield(String profield) {
        this.profield = profield;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getStatusinfo() {
        return statusinfo;
    }

    public void setStatusinfo(String statusinfo) {
        this.statusinfo = statusinfo;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getKeyfrom() {
        return keyfrom;
    }

    public void setKeyfrom(String keyfrom) {
        this.keyfrom = keyfrom;
    }

    public void shopCatFormat(ShopCar s){
        s.setPrice(WchatUtil.priceFormat(
                Integer.parseInt(s.getPrice()) , Integer.parseInt(s.getDiscount())));
        s.setDiscount(WchatUtil.priceFormat(Integer.parseInt(s.getDiscount())));
        s.setFreight(WchatUtil.priceFormat(Integer.parseInt(s.getFreight())));
        if(s.getProstatus() == 1 && s.getStock() == 0) {
            s.setProstatus(3);
            s.setStatusinfo(StatusConfig.PRODUCTSTATUS.get(s.getProstatus()));
        }else if (s.getProstatus() == 1 && s.getStock() < s.getNumber()) {
            s.setProstatus(4);
            s.setStatusinfo(StatusConfig.PRODUCTSTATUS.get(s.getProstatus()));
        }
        else
            s.setStatusinfo(StatusConfig.PRODUCTSTATUS.get(s.getProstatus()));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"proid\":\"")
                .append(proid).append('\"');
        sb.append(",\"fieldid\":\"")
                .append(fieldid).append('\"');
        sb.append(",\"prodescribe\":\"")
                .append(prodescribe).append('\"');
        sb.append(",\"price\":\"")
                .append(price).append('\"');
        sb.append(",\"prostatus\":")
                .append(prostatus);
        sb.append(",\"freight\":\"")
                .append(freight).append('\"');
        sb.append(",\"discount\":\"")
                .append(discount).append('\"');
        sb.append(",\"profield\":\"")
                .append(profield).append('\"');
        sb.append(",\"spec\":\"")
                .append(spec).append('\"');
        sb.append(",\"stock\":")
                .append(stock);
        sb.append(",\"statusinfo\":\"")
                .append(statusinfo).append('\"');
        sb.append(",\"number\":")
                .append(number);
        sb.append(",\"keyfrom\":\"")
                .append(keyfrom).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
