package cn.wodesh.controller;

import cn.wodesh.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by TS on 2018/4/21.
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/rest/order/{orderid}")
    public Object findByOrderId(@PathVariable String orderid) throws Exception{
        return orderService.findByOrderId(orderid);
    }

    @PostMapping("/rest/order/addOrder")
    public Object addOrder(@RequestBody Map map) throws Exception{
        return orderService.addOrder(map);
    }

    @PostMapping("/rest/order/cutpage")
    public Object findByOrderCut(@RequestBody Map map) throws Exception{
        return orderService.findByOrderCutPage(map);
    }

}
