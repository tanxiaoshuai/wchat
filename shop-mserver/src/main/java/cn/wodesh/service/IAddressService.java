package cn.wodesh.service;

/**
 * Created by TS on 2018/4/24.
 */
public interface IAddressService {

    public Object findByAddressList(String userid) throws Exception;

    public Object findById(String addressid) throws Exception;

}
