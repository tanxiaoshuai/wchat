package cn.wodesh.dao;

import cn.wodesh.bean.ProductTpye;
import cn.wodesh.bean.TypeChild;
import cn.wodesh.dao.sql.TemplateSQL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TS on 2018/4/18.
 */
@Repository
@Mapper
public interface ProductTpyeDao extends TemplateDao<ProductTpye>{

    @Select("select * from t_product_type ")
    @Results({
        @Result(id=true,property="ptid",column="pt_id"),
        @Result(property="ptname",column="pt_name"),
        @Result(property="status",column="pt_status"),
        @Result(property="url",column="pt_url"),
        @Result(column="pt_id",property="typeChildList",javaType=List.class,
                many=@Many(select="cn.wodesh.dao.TypeChildDao.findByTypeChildList"))
    })
    List<ProductTpye> findByProductTypeList(Class<ProductTpye> c) throws Exception;


}
