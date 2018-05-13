package cn.wodesh.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by TS on 2018/5/3.
 */

@Component
public class WchatMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(WchatMessage.class);


    public String message(String xml) throws Exception {
        JSONObject object = JSONObject.parseObject(XML.toJSONObject(xml).toString());
        object = object.getJSONObject("xml");
        LOGGER.info("微信消息：" + object);
        String Event = object.getString("Event");
        String FromUserName = object.getString("FromUserName");
        String ToUserName = object.getString("ToUserName");
        return "<xml>\n" +
                "    <ToUserName><![CDATA["+FromUserName+"]]></ToUserName>\n" +
                "    <FromUserName><![CDATA["+ToUserName+"]]></FromUserName>\n" +
                "    <CreateTime>"+System.currentTimeMillis()+"</CreateTime>\n" +
                "    <MsgType><![CDATA[news]]></MsgType>\n" +
                "    <ArticleCount>1</ArticleCount>\n" +
                "    <Articles>\n" +
                "        <item>\n" +
                "            <Title><![CDATA[温馨提示！]]></Title> \n" +
                "            <Description><![CDATA[微信商城，目前处于测试阶段，欢迎测试，所有商品不给予发货，请慎重！！！]]></Description>\n" +
                "            <PicUrl><![CDATA[http://imgsrc.baidu.com/imgad/pic/item/3b87e950352ac65c6a928859f0f2b21193138a01.jpg]]></PicUrl>\n" +
                "            <Url><![CDATA[http://www.wodesh.cn/dist/index.html]]></Url>\n" +
                "        </item>\n" +
                "    </Articles>\n" +
                "</xml> ";
    }


}
