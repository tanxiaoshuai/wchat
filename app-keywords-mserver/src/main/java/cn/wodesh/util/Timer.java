package cn.wodesh.util;
import cn.wodesh.dao.KeyWordsDao;
import cn.wodesh.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@EnableScheduling // 启用定时任务
@Component
public class Timer {

    private static Logger LOGGER = LoggerFactory.getLogger(Timer.class);

    @Autowired
    private KeyWordsDao keyWordsDao;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${hotwords_limit_time}")
    private Long hotwords_limit_time;

    @Value("${search_hotwords_size}")
    private Integer search_hotwords_size;

    public static final String APP_PRODUCT_KEYWORDS_SEARCH = "APP_KEYWORDS_MSERVER__PRODUCT_KEYWORDS_SEARCH";

    @Scheduled(fixedRate = 1000*60*5)
    public void run() throws Exception {
        long e = System.currentTimeMillis();
        long s = e - 24 * 60 * 60 * 1000L * hotwords_limit_time;
        List list = keyWordsDao.findByHotWords(search_hotwords_size , DateUtil.longForTime(s , DateUtil.YEARTOSS)
                , DateUtil.longForTime(e , DateUtil.YEARTOSS));
        LOGGER.info("查询关键字：--->" + list);
        redisUtil.set(APP_PRODUCT_KEYWORDS_SEARCH , list);
    }
}
