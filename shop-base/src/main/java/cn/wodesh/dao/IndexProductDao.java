package cn.wodesh.dao;

import cn.wodesh.bean.IndexProduct;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TS on 2018/4/25.
 */
@Repository
@Mapper
public interface IndexProductDao extends TemplateDao<IndexProduct>{

    @Select("select * from t_index_product")
    @Results({
            @Result(property = "id", column = "ip_id"),
            @Result(property = "title", column = "ip_title"),
            @Result(column = "ip_id", property = "productList", javaType = List.class,
                    many = @Many(select = "cn.wodesh.dao.ProductDao.findProductList"))
    })
    public List<IndexProduct> findIndexProductList() throws Exception;
}
