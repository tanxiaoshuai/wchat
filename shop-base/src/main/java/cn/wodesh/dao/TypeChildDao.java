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

    @Select("SELECT tc.tc_id AS tcid , tc.tc_name AS tcname , tc.tc_url AS url  FROM  t_type_child tc  WHERE tc.tc_ptid = #{pt_id} and tc.tc_status = 1 ")
    public List<TypeChild> findByTypeChildList(@Param("pt_id") String ptid) throws Exception;
}
