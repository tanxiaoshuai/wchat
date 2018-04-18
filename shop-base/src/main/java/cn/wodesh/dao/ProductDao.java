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

    @Select("<script> SELECT pr.p_id proid,pr.p_name proname,pr.p_describe prodescribe," +
            "pr.p_imgs imgs,pr.p_createtime createtime,pr.p_keywords keywords,pr.p_solds solds," +
            "pr.p_status prostatus,pr.p_freight freight,pr.p_type protype,p_typechild protypechild" +
            ",pr.p_clicks clicks,pr.p_remarks remarks," +
            "pr.p_discount discount,pr.p_info info,pr.p_extend extend , MIN(pa.pa_price) showprice " +
            "FROM t_product pr JOIN t_product_field pa ON pr.p_id=pa.pa_proid <where> 1=1 " +
            "<if test=\"type !=null and type != ''\"> and p_type = #{type} </if> " +
            "<if test=\"typechild !=null and typechild != ''\"> and p_typechild = #{typechild} </if> " +
            "<if test=\"keywords !=null and keywords != ''\"> and p_keywords like '%${keywords}%' </if> " +
            "<if test=\"status !=null and status != ''\"> and p_status = #{status}</if>" +
            "</where> group by pr.p_id limit #{startpage} , #{size} </script>")
    public List<Map> findByCutProduct(Map condition) throws Exception;

}
