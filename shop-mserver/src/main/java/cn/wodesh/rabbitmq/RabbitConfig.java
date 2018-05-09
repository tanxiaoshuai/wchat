package cn.wodesh.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String APP_KEYWORDS = "APP_KEYWORDS";
    public static final String PRODUCT_CLICKS = "PRODUCT_CLICKS";

    @Bean
    public Queue Queue() {
        return new Queue(APP_KEYWORDS);
    }

    @Bean
    public Queue Queue_Product_Clicks(){
        return new Queue(PRODUCT_CLICKS);
    }

}
