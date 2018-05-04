package cn.wodesh.controller;

import cn.wodesh.rabbitmq.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private RabbitMqSender rabbitMqSender;

    @PostMapping("/rest/test/rabbitmq")
    public Object rabbitMq(@RequestBody Map map) throws Exception{
        rabbitMqSender.send(map);
        return "成功";
    }
}
