package cn.wodesh.dao;

import cn.wodesh.bean.ProductField;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by TS on 2018/4/14.
 */
@Repository
@Mapper
public interface ProductFieldDao extends TemplateDao<ProductField>{

    @Update("update t_product_field set pa_stock = pa_stock - #{number} where pa_id = #{fieldid} and pa_stock >= #{number}")
    public int updateStock(@Param("fieldid") String fieldid , @Param("number") Integer number) throws Exception;

    @Update("update t_product_field set pa_stock = pa_stock + #{number} where pa_id = #{fieldid}")
    public int updateOrderToNumber(@Param("number") Integer number , @Param("fieldid") String fieldid) throws Exception;

    @Select("SELECT pf.pa_img AS img,pf.pa_field AS FIELD,pf.pa_spec AS spce ,p.p_name AS proname," +
            "p.p_describe AS prodescribe FROM t_product_field pf JOIN t_product p ON pf.pa_proid = p.p_id " +
            "WHERE pa_id = #{fieldid}")
    public Map selectById(@Param("fieldid") String o_paid) throws Exception;

}
