package cn.wodesh.util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@EnableScheduling
public class TokenThread {

    @Value("${default.wchat_token_open}")
    private boolean token_open;

	public static String access_token = null;

    @Scheduled(fixedRate = 1000*60*58*2)
    public void run() throws Exception {
        if(token_open)
            access_token = WchatUtil.getToken();
    }
}
