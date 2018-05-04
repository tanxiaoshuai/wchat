package cn.wodesh.rabbitmq;

import cn.wodesh.util.HttpClientUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Map map) throws Exception {
        this.rabbitTemplate.convertAndSend(RabbitConfig.APP_KEYWORDS, map);
    }
}
