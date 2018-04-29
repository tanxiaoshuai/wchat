package cn.wodesh.dao;

import cn.wodesh.bean.KeyWords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/28.
 */
@Repository
@Mapper
public interface KeyWordsDao extends TemplateDao<KeyWords>{

    @Select("SELECT  t_keyname as keyname , COUNT(1) AS count FROM t_keywords WHERE t_createtime >= #{starttime} AND t_createtime <= #{endtime} GROUP BY t_keyname ORDER BY count DESC LIMIT 0 , #{size}")
    public List<Map> findByHotWords(@Param("size") Integer size , @Param("starttime") String starttime , @Param("endtime") String endtime) throws Exception;

}
