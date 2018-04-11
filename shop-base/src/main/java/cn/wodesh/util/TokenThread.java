package cn.wodesh.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@EnableScheduling
public class TokenThread {

    private Logger LOGGER = LoggerFactory.getLogger(TokenThread.class);

	public static String access_token = null;

    @Scheduled(fixedRate = 1000*60*58*2)
    public void run() throws Exception {
        while(true){
            access_token = WchatUtil.getToken();
            if(!StringUtils.isEmpty(access_token)){
                break;
            }
        }
    }
}
