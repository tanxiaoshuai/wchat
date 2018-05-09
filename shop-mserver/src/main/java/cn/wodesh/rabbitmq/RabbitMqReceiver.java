package cn.wodesh.rabbitmq;
import cn.wodesh.service.IKeyWordService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @Autowired
    private IKeyWordService keyWordService;

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.APP_KEYWORDS)
    public void process(JSONObject object) throws Exception {
        LOGGER.info("接受关键字队列参数：" + object);
        keyWordService.save(object.toString());
    }
}
