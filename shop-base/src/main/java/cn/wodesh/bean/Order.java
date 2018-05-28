package cn.wodesh.bean;
import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import cn.wodesh.util.WchatUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by TS on 2018/4/20.
 */
@Component
@Scope("prototype")
@TableName(name = "t_order")
public class Order {

    @ID(increment = false)
    @Column(name = "o_id")
    private String orderid;

    @Column(name = "o_createtime")
    private String createtime;

    @Column(name = "o_paytime")
    private String paytime;

    @Column(name = "o_address")
    private String address;

    @Column(name = "o_tel")
    private String tel;

    @Column(name = "o_receivename")
    private String receivename;

    @Column(name = "o_paytype")
    private Integer paytype;

    @Column(name = "o_userid")
    private String userid;

    @Column(name = "o_freight")
    private String freight;

    @Column(name = "o_paycash")
    private String paycash;

    @Column(name = "o_price")
    private String price;

    @Column(name = "o_status")
    private Integer status;

    @Column(name = "o_number")
    private Integer number;

    @Column(name = "o_expcode")
    private String expcode;

    @Column(name = "o_expnumber")
    private String expnumber;

    @Column(name = "o_expname")
    private String expname;

    @Column(name = "o_payid")
    private String payid;

    @Column(name = "o_paid")
    private String paid;

    @Column(name = "o_remarks")
    private String remarks;

    @Column(name = "o_pay_status")
    private String paystatus;

    @Column(name = "o_pay_confirm_type")
    private String payconfirmtype;

    private Map productinfo;

    private String statusinfo;

    private String orderlimittime;


    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getReceivename() {
        return receivename;
    }

    public void setReceivename(String receivename) {
        this.receivename = receivename;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getPaycash() {
        return paycash;
    }

    public void setPaycash(String paycash) {
        this.paycash = paycash;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getExpcode() {
        return expcode;
    }

    public void setExpcode(String expcode) {
        this.expcode = expcode;
    }

    public String getExpnumber() {
        return expnumber;
    }

    public void setExpnumber(String expnumber) {
        this.expnumber = expnumber;
    }

    public String getExpname() {
        return expname;
    }

    public void setExpname(String expname) {
        this.expname = expname;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatusinfo() {
        return statusinfo;
    }

    public void setStatusinfo(String statusinfo) {
        this.statusinfo = statusinfo;
    }

    public String getOrderlimittime() {
        return orderlimittime;
    }

    public void setOrderlimittime(String orderlimittime) {
        this.orderlimittime = orderlimittime;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus;
    }

    public String getPayconfirmtype() {
        return payconfirmtype;
    }

    public void setPayconfirmtype(String payconfirmtype) {
        this.payconfirmtype = payconfirmtype;
    }

    public Map getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(Map productinfo) {
        this.productinfo = productinfo;
    }

    public void orderFormat(Order order){
        order.setFreight(WchatUtil.
                priceFormat(Integer.parseInt(order.getFreight())));
        order.setPrice(WchatUtil.
                priceFormat(Integer.parseInt(order.getPrice())));
        order.setPaycash(WchatUtil.
                priceFormat(Integer.parseInt(order.getPaycash())));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"orderid\":\"")
                .append(orderid).append('\"');
        sb.append(",\"createtime\":\"")
                .append(createtime).append('\"');
        sb.append(",\"paytime\":\"")
                .append(paytime).append('\"');
        sb.append(",\"address\":\"")
                .append(address).append('\"');
        sb.append(",\"tel\":\"")
                .append(tel).append('\"');
        sb.append(",\"receivename\":\"")
                .append(receivename).append('\"');
        sb.append(",\"paytype\":")
                .append(paytype);
        sb.append(",\"userid\":\"")
                .append(userid).append('\"');
        sb.append(",\"freight\":\"")
                .append(freight).append('\"');
        sb.append(",\"paycash\":\"")
                .append(paycash).append('\"');
        sb.append(",\"price\":\"")
                .append(price).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"number\":")
                .append(number);
        sb.append(",\"expcode\":\"")
                .append(expcode).append('\"');
        sb.append(",\"expnumber\":\"")
                .append(expnumber).append('\"');
        sb.append(",\"expname\":\"")
                .append(expname).append('\"');
        sb.append(",\"payid\":\"")
                .append(payid).append('\"');
        sb.append(",\"paid\":\"")
                .append(paid).append('\"');
        sb.append(",\"remarks\":\"")
                .append(remarks).append('\"');
        sb.append(",\"paystatus\":\"")
                .append(paystatus).append('\"');
        sb.append(",\"payconfirmtype\":\"")
                .append(payconfirmtype).append('\"');
        sb.append(",\"productinfo\":")
                .append(productinfo);
        sb.append(",\"statusinfo\":\"")
                .append(statusinfo).append('\"');
        sb.append(",\"orderlimittime\":\"")
                .append(orderlimittime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
