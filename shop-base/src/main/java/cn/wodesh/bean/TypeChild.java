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
}
