package cn.wodesh.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(JSONObject object){
        this.rabbitTemplate.convertAndSend(RabbitConfig.APP_KEYWORDS, object);
    }

    public void send_product_clicks(String proid){
        this.rabbitTemplate.convertAndSend(RabbitConfig.PRODUCT_CLICKS, proid);
    }
}
