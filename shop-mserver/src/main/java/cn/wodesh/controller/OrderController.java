package cn.wodesh.controller;

import cn.wodesh.service.IOrderService;
import cn.wodesh.util.ResultUtil;
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

    @GetMapping("/rest/order/selectNumber")
    public Object selectOrderCount() throws Exception{
        return orderService.selectOrderCount();
    }

    @GetMapping("/rest/order/deleteOrderId/{orderid}")
    public Object deleteOrderId(@PathVariable String orderid) throws Exception {
        return orderService.deleteOrderId(orderid);
    }

    @GetMapping("/rest/order/receiveProduct/{orderid}")
    public Object receiveProduct(@PathVariable String orderid) throws Exception {
        return orderService.receiveProduct(orderid);

    }

}
