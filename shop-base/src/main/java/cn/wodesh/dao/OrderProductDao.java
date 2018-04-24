package cn.wodesh.dao;

import cn.wodesh.bean.OrderProduct;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TS on 2018/4/21.
 */
@Mapper
@Repository
public interface OrderProductDao extends TemplateDao<OrderProduct>{

    @Select("SELECT p.p_id AS proid , p.p_describe AS prodescribe , p.p_freight AS freight ," +
            "p.p_discount AS discount , pf.pa_field AS profield , pf.pa_price AS price ," +
            "pf.pa_spec AS spec, op.op_paid AS fieldid , op.op_number AS number " +
            "FROM t_order_product op JOIN t_product_field pf ON op.op_paid = pf.pa_id JOIN t_product p " +
            "ON p.p_id=pf.pa_proid WHERE op.op_orderid = #{orderid}")
    public List<OrderProduct> findOrderProductList(@Param("o_id") String orderid) throws Exception;
}
