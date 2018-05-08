package cn.wodesh.service.impl;

import cn.wodesh.bean.Address;
import cn.wodesh.dao.AddressDao;
import cn.wodesh.service.IAddressService;
import cn.wodesh.util.ParamValidateUtil;
import cn.wodesh.util.ResultUtil;
import cn.wodesh.util.TokenUtil;
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
    public Object findByAddressList() throws Exception {
        StringBuffer param = new StringBuffer().append("a_userid = '").append(TokenUtil.tokenGetUser().getUserid()).append("'");
        List<Address> list =  addressDao.findBySQLRequireToList(param.toString() , Address.class);
        return ResultUtil.success(list);
    }

    @Override
    public Object findById(String addressid) throws Exception {
        Address address = addressDao.findById(addressid , Address.class);
        return ResultUtil.success(address);
    }

    @Override
    public Object updateAddress(Address address) throws Exception {
        ParamValidateUtil.notNull(address.getAddressid() , "地址id不能为空");
        addressDao.updateById(address);
        return ResultUtil.success();
    }

    @Override
    public Object deleteAddress(String addressid) throws Exception {
        ParamValidateUtil.notNull(addressid , "地址id不能为空");
        addressDao.deleteById(addressid , Address.class);
        return ResultUtil.success();
    }

}
