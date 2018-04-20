package cn.wodesh.controller;

import cn.wodesh.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TS on 2018/4/19.
 */
@RestController
public class ShopCarController {

    @Autowired
    private IShopCarService shopCarService;

    @GetMapping("/rest/shopcar/search")
    public Object findShopCarList(@RequestParam String userid,
                                  @RequestParam Integer page,
                                  @RequestParam Integer size) throws Exception{
        return shopCarService.findShopCarList(userid , page , size);
    }

    @GetMapping("/rest/shopcar/updatenumber")
    public Object changeNumber(@RequestParam Integer number,
                                  @RequestParam String userid,
                                  @RequestParam String fieldid) throws Exception{
        return shopCarService.changeNumber(number , userid , fieldid);
    }
}