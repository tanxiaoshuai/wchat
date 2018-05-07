package cn.wodesh.controller;

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

    @GetMapping("/rest/shopcar/updatenumber")
    public Object changeNumber(@RequestParam Integer number,
                                  @RequestParam String userid,
                                  @RequestParam String fieldid) throws Exception{
        return shopCarService.changeNumber(number , userid , fieldid);
    }

    @PostMapping("/rest/shopcar/add")
    public Object add(@RequestBody Map map) throws Exception{
        return shopCarService.save(map);
    }

    @PostMapping("/rest/shopcar/checkproducts")
    public Object checkProduct(@RequestBody List<String> list) throws Exception{
        return shopCarService.checkProduct(list);
    }
}
