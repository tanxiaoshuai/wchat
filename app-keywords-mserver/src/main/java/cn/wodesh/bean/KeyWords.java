package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by TS on 2018/4/28.
 */
@Component
@Scope("prototype")
@TableName(name = "t_keywords")
public class KeyWords {

    @ID
    @Column(name = "t_keyid")
    private String keyid;

    @Column(name = "t_keyname")
    private String keyname;

    @Column(name = "t_keynumber")
    private Long keynumber;

    @Column(name = "t_createtime")
    private String createtime;

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public Long getKeynumber() {
        return keynumber;
    }

    public void setKeynumber(Long keynumber) {
        this.keynumber = keynumber;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"keyid\":\"")
                .append(keyid).append('\"');
        sb.append(",\"keyname\":\"")
                .append(keyname).append('\"');
        sb.append(",\"keynumber\":")
                .append(keynumber);
        sb.append(",\"createtime\":\"")
                .append(createtime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
