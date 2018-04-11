package cn.wodesh.Test;

import cn.wodesh.config.WchatConfig;
import cn.wodesh.util.HttpClientUtil;

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println(HttpClientUtil.getHttps(WchatConfig.WX_TOKEN_URL , null));
    }
}
