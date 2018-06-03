package cn.wodesh.controller;

import cn.wodesh.service.IPayService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by TS on 2018/5/5.
 */
@RestController
public class PayController {

    @Autowired
    private IPayService payService;

    @GetMapping("/rest/pay/orderid")
    public Object pay(@RequestParam String orderid) throws Exception{
        return payService.pay(orderid);
    }

    @PostMapping(value = "/rest/pay/callback" , produces = MediaType.APPLICATION_XML_VALUE)
    public String callback(@RequestBody String body , HttpServletResponse response) throws Exception{
        return (String) payService.payCallback(body);
    }
}
