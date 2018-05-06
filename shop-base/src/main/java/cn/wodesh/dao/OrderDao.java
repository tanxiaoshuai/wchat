package cn.wodesh.dao;

import cn.wodesh.bean.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/20.
 */
@Mapper
@Repository
public interface OrderDao extends TemplateDao<Order>{

    @Select("select * from t_order where o_id = #{orderid}")
    @Results({
            @Result(id = true, property = "createtime", column = "o_createtime"),
            @Result(property = "paytime", column = "o_paytime"),
            @Result(property = "address", column = "o_address"),
            @Result(property = "tel", column = "o_tel"),
            @Result(property = "receivename", column = "o_receivename"),
            @Result(property = "paytype", column = "o_paytype"),
            @Result(property = "userid", column = "o_userid"),
            @Result(property = "cash", column = "o_cash"),
            @Result(property = "freight", column = "o_freight"),
            @Result(property = "status", column = "o_status"),
            @Result(property = "remarks", column = "o_remarks"),
            @Result(property = "orderid", column = "o_id"),
    })
    public Order findByOrderId(@Param("orderid") String orderid) throws Exception;

}
