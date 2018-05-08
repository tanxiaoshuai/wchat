package cn.wodesh.rabbitmq;
import cn.wodesh.bean.KeyWords;
import cn.wodesh.service.impl.KeyServiceImpl;
import cn.wodesh.util.BeanFactoryUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqReceiver.class);

    public static final String APP_KEYWORDS = "APP_KEYWORDS";

    @RabbitHandler
    @RabbitListener(queues = APP_KEYWORDS)
    public void process(JSONObject object) throws Exception {
        LOGGER.info("接受关键字队列参数：" + object);
        KeyWords keyWords = JSONObject.parseObject(object.toString() , KeyWords.class);
        KeyServiceImpl keyService = BeanFactoryUtil.getBeanByClass(KeyServiceImpl.class);
        keyService.save(keyWords);
    }
}
