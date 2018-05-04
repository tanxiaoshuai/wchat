package cn.wodesh.rabbitmq;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = RabbitConfig.APP_KEYWORDS)
public class RabbitMqReceiver {

    @RabbitHandler
    public void process(Map map) throws Exception {
        System.out.println("Receiver  收到: " + JSONObject.toJSONString(map));
    }
}
