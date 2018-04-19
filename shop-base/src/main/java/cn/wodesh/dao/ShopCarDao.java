package cn.wodesh.dao;

import cn.wodesh.bean.ShopCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TS on 2018/4/19.
 */
@Repository
@Mapper
public interface ShopCarDao extends TemplateDao<ShopCar>{
    @Select("SELECT p.p_id AS proid , p.p_describe AS prodescribe , p.p_status AS prostatus , p.p_freight AS freight ," +
            " p.p_discount AS discount , pf.pa_field AS profield , pf.pa_price AS price ," +
            " pf.pa_spec AS spec, pf.pa_stock AS stock , sp.sp_paid AS fieldid , sp.sp_number AS number " +
            "FROM t_shopcar sp JOIN t_product_field pf ON sp.sp_paid = pf.pa_id " +
            "JOIN t_product p ON pf.pa_proid = p.p_id WHERE sp.sp_userid = #{userid} limit #{startpage} , #{size}")
    public List<ShopCar> findShopCarList(@Param("userid") String userid , @Param("startpage") Integer startpage , @Param("size") Integer size) throws Exception;

    @Update("<script>update t_shopcar set sp_number = sp_number + #{number} " +
            "where sp_userid = #{userid} and sp_paid = #{fieldid} " +
            "<if test=\"number == -1\"> and sp_number > 1 </if> </script>")
    public void changeNumber (@Param("number") Integer number , @Param("userid") String userid , @Param("fieldid") String fieldid) throws Exception;
}
