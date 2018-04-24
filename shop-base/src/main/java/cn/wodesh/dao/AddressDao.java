package cn.wodesh.dao;

import cn.wodesh.bean.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/4/24.
 */
@Repository
@Mapper
public interface AddressDao extends TemplateDao<Address>{

}
