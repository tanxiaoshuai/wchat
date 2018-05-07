package cn.wodesh.dao;

import cn.wodesh.bean.ShopCar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/19.
 */
@Repository
@Mapper
public interface ShopCarDao extends TemplateDao<ShopCar>{
    @Select("SELECT p.p_id AS proid , p.p_describe AS prodescribe , p.p_status AS prostatus , p.p_freight AS freight , sp.sp_id as shopcarid , " +
            " p.p_discount AS discount , pf.pa_field AS profield , pf.pa_price AS price , p.p_keyfrom AS keyfrom , " +
            " pf.pa_spec AS spec, pf.pa_stock AS stock , sp.sp_paid AS fieldid , sp.sp_number AS number " +
            "FROM t_shopcar sp JOIN t_product_field pf ON sp.sp_paid = pf.pa_id " +
            "JOIN t_product p ON pf.pa_proid = p.p_id WHERE sp.sp_userid = #{userid} limit #{startpage} , #{size}")
    public List<ShopCar> findShopCarList(@Param("userid") String userid , @Param("startpage") Integer startpage , @Param("size") Integer size) throws Exception;

    @Select("SELECT p.p_id AS proid , p.p_describe AS prodescribe , p.p_status AS prostatus , p.p_freight AS freight , sp.sp_id as shopcarid , " +
            " p.p_discount AS discount , pf.pa_field AS profield , pf.pa_price AS price , p.p_keyfrom AS keyfrom , " +
            " pf.pa_spec AS spec, pf.pa_stock AS stock , sp.sp_paid AS fieldid , sp.sp_number AS number " +
            "FROM t_shopcar sp JOIN t_product_field pf ON sp.sp_paid = pf.pa_id " +
            "JOIN t_product p ON pf.pa_proid = p.p_id WHERE sp.sp_id = #{shopcarid}")
    public ShopCar findShopCarBean(@Param("shopcarid") String shopcarid) throws Exception;

    @Insert("insert into t_shopcar (sp_id , sp_userid , sp_paid , sp_number) values (#{shopcarid} , #{userid} , #{fieldid} , #{number})")
    public void add(Map map)throws Exception;

    @Select("select sp_number as number , sp_id as shopcarid from t_shopcar where sp_userid = #{userid} and sp_paid = #{fieldid}")
    public Map findShopCarProductNumberOrshopCarId(Map map) throws Exception;

    @Update("update t_shopcar set sp_number = sp_number + #{number} where sp_id = #{shopcarid}")
    public Integer updateNumber (Map map) throws Exception;

}
