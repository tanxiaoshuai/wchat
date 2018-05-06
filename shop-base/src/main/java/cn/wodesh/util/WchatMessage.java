package cn.wodesh.util;
import com.alibaba.fastjson.JSONObject;
/**
 * Created by TS on 2018/5/3.
 */
public class WchatMessage {

    public static String message(String xml) throws Exception {
        JSONObject object = JSONObject.parseObject(XML.toJSONObject(xml).toString());
        JSONObject objxml = object.getJSONObject("xml");
        String Event = objxml.getString("Event");
        System.out.println(Event);
        if(Event !=null && !"subscribe".equals(Event)){
            return "";
        }

        String FromUserName = objxml.getString("FromUserName");
        String ToUserName = objxml.getString("ToUserName");
        objxml.put("FromUserName" , ToUserName);
        objxml.put("ToUserName" , FromUserName);
        objxml.put("Content" , "你好");
        object.put("xml" , objxml);
        return XmlUtil.JsonToXml(object.toString());
    }
}
