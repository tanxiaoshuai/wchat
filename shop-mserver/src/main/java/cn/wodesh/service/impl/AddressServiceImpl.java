package cn.wodesh.service.impl;

import cn.wodesh.bean.Address;
import cn.wodesh.dao.AddressDao;
import cn.wodesh.service.IAddressService;
import cn.wodesh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TS on 2018/4/24.
 */
@Service
public class AddressServiceImpl implements IAddressService{

    @Autowired
    private AddressDao addressDao;

    @Override
    public Object findByAddressList(String userid) throws Exception {
        StringBuffer param = new StringBuffer().append("a_userid = '").append(userid).append("'");
        List<Address> list =  addressDao.findBySQLRequireToList(param.toString() , Address.class);
        return ResultUtil.success(list);
    }

    @Override
    public Object findById(String addressid) throws Exception {
        Address address = addressDao.findById(addressid , Address.class);
        return ResultUtil.success(address);
    }
}
