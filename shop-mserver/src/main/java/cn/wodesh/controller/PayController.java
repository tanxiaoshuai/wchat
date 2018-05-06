package cn.wodesh.controller;

import cn.wodesh.service.IPayService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by TS on 2018/5/5.
 */
@RestController
public class PayController {

    @Autowired
    private IPayService payService;

    @PostMapping("/rest/pay")
    public Object pay(@RequestBody String body) throws Exception{
        Map map = JSONObject.parseObject(body , Map.class);
        return payService.pay(map);
    }

    @PostMapping(value = "/rest/pay/callback" , produces = MediaType.APPLICATION_XML_VALUE)
    public Object callback(@RequestBody String body) throws Exception{
        return payService.payCallback(body);
    }
}
