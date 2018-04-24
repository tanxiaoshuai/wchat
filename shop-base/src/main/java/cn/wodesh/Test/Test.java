package cn.wodesh.Test;

import cn.wodesh.config.WchatConfig;
import cn.wodesh.util.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;

public class Test {

    public static void main(String[] args) throws Exception {
//        System.out.println(HttpClientUtil.getHttps(WchatConfig.WX_TOKEN_URL , null));
        System.out.println(String.format("%.1f", 1.07432));
        String json = "[\n" +
                "  {\n" +
                "    \"radio_name\":\"卖批1\",\n" +
                "    \"radio_url\":\"https:wodesh.cn\",\n" +
                "    \"radio_logo_url\":\"www\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"radio_name\":\"卖批\",\n" +
                "    \"radio_url\":\"https:wodesh.cn\",\n" +
                "    \"radio_logo_url\":\"www\"\n" +
                "  }\n" +
                "]  ";
        JSONArray arr = JSONArray.parseArray(json);
        System.out.println(HttpClientUtil.postHeader(json.toString() , "http://1e6d0e43.ngrok.lu8.win:83/rest/radio/save" , null));
    }
}
