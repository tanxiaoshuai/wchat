package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by TS on 2018/4/18.
 */
@Component
@Scope("prototype")
@TableName(name = "t_type_child")
public class TypeChild {

    @ID(increment = false)
    @Column(name = "tc_id")
    private String tcid;

    @Column(name = "tc_name")
    private String tcname;

    @Column(name = "tc_url")
    private String url;

    @Column(name = "tc_status")
    private Integer status;

    @Column(name = "tc_ptid")
    private String ptid;



    public String getTcid() {
        return tcid;
    }

    public void setTcid(String tcid) {
        this.tcid = tcid;
    }

    public String getTcname() {
        return tcname;
    }

    public void setTcname(String tcname) {
        this.tcname = tcname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPtid() {
        return ptid;
    }

    public void setPtid(String ptid) {
        this.ptid = ptid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"tcid\":\"")
                .append(tcid).append('\"');
        sb.append(",\"tcname\":\"")
                .append(tcname).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"ptid\":\"")
                .append(ptid).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
