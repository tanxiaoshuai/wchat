package cn.wodesh.dao;

import cn.wodesh.bean.ProductField;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/4/14.
 */
@Repository
@Mapper
public interface ProductFieldDao extends TemplateDao<ProductField>{


}
