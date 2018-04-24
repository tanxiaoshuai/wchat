package cn.wodesh.controller;

import cn.wodesh.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
