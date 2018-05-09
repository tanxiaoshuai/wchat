package cn.wodesh.controller;

import cn.wodesh.bean.ShopCar;
import cn.wodesh.service.IShopCarService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/4/19.
 */
@RestController
public class ShopCarController {

    @Autowired
    private IShopCarService shopCarService;

    @GetMapping("/rest/shopcar/search")
    public Object findShopCarList(@RequestParam Integer page,
                                  @RequestParam Integer size) throws Exception{
        return shopCarService.findShopCarList(page , size);
    }

    @PostMapping("/rest/shopcar/updatenumber")
    public Object changeNumber(@RequestBody List<ShopCar> shopCars) throws Exception{
        return shopCarService.changeNumber(shopCars);
    }

    @PostMapping("/rest/shopcar/add")
    public Object add(@RequestBody Map map) throws Exception{
        return shopCarService.save(map);
    }

    @PostMapping("/rest/shopcar/checkproducts")
    public Object checkProduct(@RequestBody List<String> list) throws Exception{
        return shopCarService.checkProduct(list);
    }

    @GetMapping("/rest/shopcar/orderInfo/{outid}")
    public Object orderInfo(@PathVariable String outid) throws Exception{
        return shopCarService.ShopCarToOderInfo(outid);
    }
}
