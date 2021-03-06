package cn.wodesh.service;

import cn.wodesh.bean.Address;

import java.util.Map;

/**
 * Created by TS on 2018/4/24.
 */
public interface IAddressService {

    public Object findByAddressList() throws Exception;

    public Object findById(String addressid) throws Exception;

    public Object updateAddress(Address address) throws Exception;

    public Object deleteAddress(String addressid)throws Exception;

    public Object save(Address address)throws Exception;

    public Object updateAddressStatus(Map body) throws Exception;

}
