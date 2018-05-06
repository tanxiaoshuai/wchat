package cn.wodesh.dao;

import cn.wodesh.bean.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by TS on 2018/4/24.
 */
@Repository
@Mapper
public interface AddressDao extends TemplateDao<Address>{

    @Select("SELECT a_id addressid,a_province province,a_city city,a_addressinfo addressinfo,a_tel tel,a_receivename receivename,a_postcode postcode,a_status status,a_userid userid FROM t_address WHERE a_userid = #{userid} AND a_status = #{status}")
    public Address findUserToDefualtAddress(@Param("userid") String userid , @Param("status") Integer status) throws Exception;

}
