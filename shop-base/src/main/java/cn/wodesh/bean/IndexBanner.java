package cn.wodesh.bean;

import cn.wodesh.dao.annotation.Column;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by TS on 2018/4/25.
 */
@Component
@Scope("prototype")
@TableName(name = "t_index_banner")
public class IndexBanner {

    @ID(increment = false)
    @Column(name = "ib_id")
    private String bannerid;
    @Column(name = "ib_img")
    private String img;
    @Column(name = "ib_url")
    private String url;

    public String getBannerid() {
        return bannerid;
    }

    public void setBannerid(String bannerid) {
        this.bannerid = bannerid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"bannerid\":\"")
                .append(bannerid).append('\"');
        sb.append(",\"img\":\"")
                .append(img).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
