package cn.wodesh.dao;

import cn.wodesh.bean.ProductField;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/4/14.
 */
@Repository
@Mapper
public interface ProductFieldDao extends TemplateDao<ProductField>{

    @Update("update t_product_field set pa_stock = pa_stock - #{number} where pa_id = #{fieldid} and pa_stock >= #{number}")
    public int updateStock(@Param("fieldid") String fieldid , @Param("number") Integer number) throws Exception;

}
