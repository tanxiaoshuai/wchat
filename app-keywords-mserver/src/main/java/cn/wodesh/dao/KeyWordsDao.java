package cn.wodesh.dao;

import cn.wodesh.bean.KeyWords;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/4/28.
 */
@Repository
@Mapper
public interface KeyWordsDao extends TemplateDao<KeyWords>{


}
