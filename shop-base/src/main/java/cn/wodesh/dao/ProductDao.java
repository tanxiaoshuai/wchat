package cn.wodesh.dao;

import cn.wodesh.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/4/12.
 */
@Repository
@Mapper
public interface ProductDao extends TemplateDao<Product>{


}
