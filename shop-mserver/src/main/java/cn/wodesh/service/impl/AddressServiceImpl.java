package cn.wodesh.service.impl;

import cn.wodesh.bean.Address;
import cn.wodesh.dao.AddressDao;
import cn.wodesh.service.IAddressService;
import cn.wodesh.util.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Override
    public Object save(Address address) throws Exception {
        address.setAddressid(KeyUtil.uuid());
        address.setUserid(TokenUtil.tokenGetUser().getUserid());
        address.setStatus(0);
        addressDao.save(address);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Object updateAddressStatus(Map body) throws Exception {
        JSONObject obj = (JSONObject) JSONObject.toJSON(body);
        String oldaddressid = obj.getString("oldaddressid");
        String newaddressid = obj.getString("newaddressid");
        ParamValidateUtil.notNull(oldaddressid , "oldaddressid参数不能为空");
        ParamValidateUtil.notNull(newaddressid , "newaddressid参数不能为空");
        Address address = BeanFactoryUtil.getBeanByClass(Address.class);
        address.setAddressid(oldaddressid);
        address.setStatus(0);
        addressDao.updateById(address);
        address.setAddressid(newaddressid);
        address.setStatus(1);
        addressDao.updateById(address);
        return ResultUtil.success();
    }

}
