package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TS on 2018/4/18.
 */
@Component
@Scope("prototype")
@TableName(name = "t_product_type")
public class ProductTpye {

    @ID(increment = false)
    @Column(name = "pt_id")
    private String ptid;

    @Column(name = "pt_name")
    private String ptname;

    @Column(name = "pt_status")
    private Integer status;

    private List<TypeChild> typeChildList;

    public String getPtid() {
        return ptid;
    }

    public void setPtid(String ptid) {
        this.ptid = ptid;
    }

    public String getPtname() {
        return ptname;
    }

    public void setPtname(String ptname) {
        this.ptname = ptname;
    }

    public List<TypeChild> getTypeChildList() {
        return typeChildList;
    }

    public void setTypeChildList(List<TypeChild> typeChildList) {
        this.typeChildList = typeChildList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"ptid\":\"")
                .append(ptid).append('\"');
        sb.append(",\"ptname\":\"")
                .append(ptname).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"typeChildList\":")
                .append(typeChildList);
        sb.append('}');
        return sb.toString();
    }
}
