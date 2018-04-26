package cn.wodesh.bean;

import cn.wodesh.config.StatusConfig;
import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import cn.wodesh.util.WchatUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private String paytype;
    @Column(name = "o_userid")
    private String userid;
    @Column(name = "o_cash")
    private String cash;
    @Column(name = "o_freight")
    private String freight;
    @Column(name = "o_status")
    private Integer status;

    private String statusinfo;

    private String remarks;

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

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusinfo() {
        return statusinfo;
    }

    public void setStatusinfo(String statusinfo) {
        this.statusinfo = statusinfo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void orderFormat(Order order){
        order.setFreight(WchatUtil.priceFormat(Integer.parseInt(order.getFreight())));
        order.setCash(WchatUtil.priceFormat(Integer.parseInt(order.getCash())));
        order.setStatusinfo(StatusConfig.ORDERSTATUS.get(order.getStatus()));
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
        sb.append(",\"paytype\":\"")
                .append(paytype).append('\"');
        sb.append(",\"userid\":\"")
                .append(userid).append('\"');
        sb.append(",\"cash\":\"")
                .append(cash).append('\"');
        sb.append(",\"freight\":\"")
                .append(freight).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"statusinfo\":\"")
                .append(statusinfo).append('\"');
        sb.append(",\"remarks\":\"")
                .append(remarks).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
