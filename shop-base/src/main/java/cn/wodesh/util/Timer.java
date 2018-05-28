package cn.wodesh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling // 启用定时任务
public class Timer {

    private static Logger LOGGER = LoggerFactory.getLogger(Timer.class);

    @Scheduled(fixedRate = 1000*60)
    public void scheduler() throws Exception {

    }
}
