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
            @Result(property = "createtime", column = "o_createtime"),
            @Result(property = "paytime", column = "o_paytime"),
            @Result(property = "address", column = "o_address"),
            @Result(property = "tel", column = "o_tel"),
            @Result(property = "receivename", column = "o_receivename"),
            @Result(property = "paytype", column = "o_paytype"),
            @Result(property = "userid", column = "o_userid"),
            @Result(property = "price", column = "o_price"),
            @Result(property = "freight", column = "o_freight"),
            @Result(property = "status", column = "o_status"),
            @Result(property = "remarks", column = "o_remarks"),
            @Result(property = "orderid", column = "o_id"),
            @Result(property = "paycash", column = "o_paycash"),
            @Result(property = "number", column = "o_number"),
            @Result(property = "expcode", column = "o_expcode"),
            @Result(property = "expnumber", column = "o_expnumber"),
            @Result(property = "expname", column = "o_expname"),
            @Result(property = "payid", column = "o_payid"),
            @Result(property = "paid", column = "o_paid"),
            @Result(property = "paystatus", column = "o_pay_status"),
            @Result(property = "payconfirmtype", column = "o_pay_confirm_type"),
    })
    public Order findByOrderId(@Param("orderid") String orderid) throws Exception;

    /**
     * 订单失效 库存还原 查询商品数量
     * @param payid
     * @param status
     * @return
     * @throws Exception
     */
    @Select("select o_paid as fieldid , o_number as number from t_order where o_payid = #{payid} and o_status= #{status}")
    public List<Map> findByOrderIdAndNumber(@Param("payid") String payid , @Param("status") Integer status) throws Exception;


    @Select("<script> select * from t_order where o_userid = #{userid} <if test=\"status !=null and status != '' \"> and o_status = #{status} </if> and o_status != 0 order by o_createtime desc limit #{startpage} , #{size} </script> ")
    @Results({
            @Result(property = "createtime", column = "o_createtime"),
            @Result(property = "paytime", column = "o_paytime"),
            @Result(property = "address", column = "o_address"),
            @Result(property = "tel", column = "o_tel"),
            @Result(property = "receivename", column = "o_receivename"),
            @Result(property = "paytype", column = "o_paytype"),
            @Result(property = "userid", column = "o_userid"),
            @Result(property = "price", column = "o_price"),
            @Result(property = "freight", column = "o_freight"),
            @Result(property = "status", column = "o_status"),
            @Result(property = "remarks", column = "o_remarks"),
            @Result(property = "orderid", column = "o_id"),
            @Result(property = "paycash", column = "o_paycash"),
            @Result(property = "number", column = "o_number"),
            @Result(property = "expcode", column = "o_expcode"),
            @Result(property = "expnumber", column = "o_expnumber"),
            @Result(property = "expname", column = "o_expname"),
            @Result(property = "paystatus", column = "o_pay_status"),
            @Result(property = "payconfirmtype", column = "o_pay_confirm_type"),
            @Result(property = "payid", column = "o_payid"),
            @Result(property = "paid", column = "o_paid" ),
            @Result(column = "o_paid" , property = "productinfo",javaType = Map.class,
                    one = @One(select = "cn.wodesh.dao.ProductFieldDao.selectById"))
    })
    public List<Order> findByOrderPage(Map map)throws Exception;

    @Update("update t_order set o_status = #{status} where o_id = #{orderid}")
    public void updateStatus(@Param("orderid") String orderid , @Param("status") Integer status) throws Exception;

}
