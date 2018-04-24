package cn.wodesh.bean;

/**
 * Created by TS on 2018/4/20.
 */
public class OrderProduct {

    private String proid;

    private String fieldid;

    private String prodescribe;

    private String price;

    private String freight;

    private String discount;

    private String profield;

    private String spec;

    private Integer number;


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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        sb.append(",\"freight\":\"")
                .append(freight).append('\"');
        sb.append(",\"discount\":\"")
                .append(discount).append('\"');
        sb.append(",\"profield\":\"")
                .append(profield).append('\"');
        sb.append(",\"spec\":\"")
                .append(spec).append('\"');
        sb.append(",\"number\":")
                .append(number);
        sb.append('}');
        return sb.toString();
    }
}
