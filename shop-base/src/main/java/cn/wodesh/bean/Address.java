package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by TS on 2018/4/24.
 */
@Component
@Scope("prototype")
@TableName(name = "t_address")
public class Address {

    @ID(increment = false)
    @Column(name = "a_id")
    private String addressid;
    @Column(name = "a_province")
    private String province;
    @Column(name = "a_city")
    private String city;
    @Column(name = "a_addressinfo")
    private String addressinfo;
    @Column(name = "a_tel")
    private String tel;
    @Column(name = "a_receivename")
    private String receivename;
    @Column(name = "a_postcode")
    private String postcode;
    @Column(name = "a_status")
    private Integer status;
    @Column(name = "a_userid")
    private String userid;
    @Column(name = "a_area")
    private String area;

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressinfo() {
        return addressinfo;
    }

    public void setAddressinfo(String addressinfo) {
        this.addressinfo = addressinfo;
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"addressid\":\"")
                .append(addressid).append('\"');
        sb.append(",\"province\":\"")
                .append(province).append('\"');
        sb.append(",\"city\":\"")
                .append(city).append('\"');
        sb.append(",\"addressinfo\":\"")
                .append(addressinfo).append('\"');
        sb.append(",\"tel\":\"")
                .append(tel).append('\"');
        sb.append(",\"receivename\":\"")
                .append(receivename).append('\"');
        sb.append(",\"postcode\":\"")
                .append(postcode).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"userid\":\"")
                .append(userid).append('\"');
        sb.append(",\"area\":\"")
                .append(area).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
