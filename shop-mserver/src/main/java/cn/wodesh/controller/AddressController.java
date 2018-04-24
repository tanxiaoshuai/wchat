package cn.wodesh.controller;

import cn.wodesh.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TS on 2018/4/24.
 */
@RestController
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping("/rest/address/search/{userid}")
    public Object findByAddressList(@PathVariable String userid) throws Exception{
        return addressService.findByAddressList(userid);
    }

    @GetMapping("/rest/address/findById/{addressid}")
    public Object findById(@PathVariable String addressid) throws Exception{
        return addressService.findById(addressid);
    }



}
