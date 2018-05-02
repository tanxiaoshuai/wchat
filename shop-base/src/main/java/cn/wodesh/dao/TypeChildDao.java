package cn.wodesh.dao;

import cn.wodesh.bean.TypeChild;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TS on 2018/4/18.
 */
@Mapper
@Repository
public interface TypeChildDao extends TemplateDao<TypeChild>{

    @Select("SELECT tc.tc_id as tcid , tc.tc_name as tcname , tc.tc_url as url  FROM t_product_type_child  pt JOIN t_type_child tc ON pt.ptc_tcid = tc.tc_id WHERE pt.ptc_ptid = #{ptid}")
    public List<TypeChild> findByTypeChildList(@Param("pt_id") String ptid) throws Exception;
}
