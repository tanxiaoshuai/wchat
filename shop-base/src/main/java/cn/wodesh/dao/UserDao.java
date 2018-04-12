package cn.wodesh.dao;
import cn.wodesh.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/4/11.
 */
@Repository
@Mapper
public interface UserDao extends TemplateDao<User>{

}
