package cn.wodesh.dao;

import cn.wodesh.bean.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TS on 2018/4/20.
 */
@Mapper
@Repository
public interface OrderDao extends TemplateDao<Order>{

    @Select("select * from t_order where o_id = #{orderid}")
    @Results({
            @Result(id = true, property = "createtime", column = "o_createtime"),
            @Result(id = true, property = "paytime", column = "o_paytime"),
            @Result(id = true, property = "address", column = "o_address"),
            @Result(id = true, property = "tel", column = "o_tel"),
            @Result(id = true, property = "receivename", column = "o_receivename"),
            @Result(id = true, property = "paytype", column = "o_paytype"),
            @Result(id = true, property = "userid", column = "o_userid"),
            @Result(id = true, property = "cash", column = "o_cash"),
            @Result(id = true, property = "freight", column = "o_freight"),
            @Result(id = true, property = "status", column = "o_status"),
            @Result(id = true, property = "remarks", column = "o_remarks"),
            @Result(id = true, property = "orderid", column = "o_id"),
            @Result(column = "o_id", property = "orderProducts", javaType = List.class,
                    many = @Many(select = "cn.wodesh.dao.OrderProductDao.findOrderProductList"))
    })
    public Order findByOrderId(@Param("orderid") String orderid) throws Exception;
}
