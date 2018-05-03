package cn.wodesh.dao;
import cn.wodesh.bean.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/12.
 */
@Repository
@Mapper
public interface ProductDao extends TemplateDao<Product>{

    @Select("<script> SELECT pr.p_id proid,pr.p_name proname,pr.p_describe prodescribe,pr.p_keyfrom keyfrom," +
            "pr.p_imgs imgs,pr.p_createtime createtime,pr.p_keywords keywords,pr.p_solds solds," +
            "pr.p_status prostatus,pr.p_freight freight,pr.p_type protype,p_typechild protypechild" +
            ",pr.p_clicks clicks,pr.p_remarks remarks," +
            "pr.p_discount discount,pr.p_info info,pr.p_extend extend , MIN(pa.pa_price) showprice " +
            "FROM t_product pr JOIN t_product_field pa ON pr.p_id=pa.pa_proid <where> 1=1 " +
            "<if test=\"type !=null and type != ''\"> and p_type = #{type} </if> " +
            "<if test=\"typechild !=null and typechild != ''\"> and p_typechild = #{typechild} </if> " +
            "<if test=\"keywords !=null and keywords != ''\"> and p_keywords like '%${keywords}%' </if> " +
            "<if test=\"status !=null and status != ''\"> and p_status = #{status}</if>" +
            "</where> group by pr.p_id ORDER BY pr.p_createtime DESC limit #{startpage} , #{size} </script>")
    public List<Map> findByCutProduct(Map condition) throws Exception;

    @Select("SELECT p.p_id proid,p.p_name proname,p.p_describe prodescribe,p.p_keyfrom keyfrom,p.p_imgs imgs," +
            "p.p_createtime createtime,p.p_keywords keywords,p.p_status prostatus,p.p_freight freight,p.p_type protype," +
            "p.p_typechild protypechild,p.p_discount discount,p.p_clicks clicks,p.p_remarks remarks,p.p_solds solds," +
            "p.p_info info,p.p_extend extend , MIN(pa.pa_price) showprice FROM t_index_product_info ip JOIN t_product p " +
            "ON ip.in_proid = p.p_id JOIN t_product_field pa ON p.p_id = pa.pa_proid " +
            "WHERE ip.in_status = 1 AND p.p_status = 1 AND  ip.in_ipid = #{ipid} GROUP BY p.p_id")
    public List<Map> findProductList(@Param("ip_id") String ipid) throws Exception;

    @Select("SELECT p.p_id proid,p.p_name proname,p.p_describe prodescribe,p.p_keyfrom keyfrom,p.p_imgs imgs," +
            "p.p_createtime createtime,p.p_keywords keywords,p.p_status prostatus,p.p_freight freight,p.p_type protype," +
            "p.p_typechild protypechild,p.p_discount discount,p.p_clicks clicks,p.p_remarks remarks,p.p_solds solds," +
            "p.p_info info,p.p_extend extend , MIN(pa.pa_price) showprice FROM t_index_product_info ip JOIN t_product p " +
            "ON ip.in_proid = p.p_id JOIN t_product_field pa ON p.p_id = pa.pa_proid " +
            "WHERE p.p_status = 1 AND  ip.in_ipid = #{ipid} GROUP BY p.p_id ORDER BY p.p_createtime DESC limit #{startpage} , #{size}")
    public List<Map> findProductListCutPage(Map map) throws Exception;

}
