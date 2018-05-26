package cn.wodesh.controller;

import cn.wodesh.bean.Address;
import cn.wodesh.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by TS on 2018/4/24.
 */
@RestController
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping("/rest/address/search")
    public Object findByAddressList() throws Exception{
        return addressService.findByAddressList();
    }

    @GetMapping("/rest/address/findById/{addressid}")
    public Object findById(@PathVariable String addressid) throws Exception{
        return addressService.findById(addressid);
    }

    @PostMapping("/rest/address/update")
    public Object updateAddres (@RequestBody Address address) throws Exception{
        return addressService.updateAddress(address);
    }

    @PostMapping("/rest/address/delete/{addressid}")
    public Object updateAddres (@PathVariable String addressid) throws Exception{
        return addressService.deleteAddress(addressid);
    }

    @PostMapping("/rest/address/save")
    public Object save(@RequestBody Address address) throws Exception{
        return addressService.save(address);
    }

    @PostMapping("/rest/address/update/status")
    public Object updatestatus(@RequestBody Map map) throws Exception{
        return addressService.updateAddressStatus(map);
    }

}
