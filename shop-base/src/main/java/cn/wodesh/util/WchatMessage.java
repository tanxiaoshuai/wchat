package cn.wodesh.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by TS on 2018/5/3.
 */
public class WchatMessage {

    public static String message(HttpServletRequest request) throws Exception {
        String reqxml = IoUtil.IoToString(request.getReader());
        JSONObject object = XML.toJSONObject(reqxml);
        JSONObject objxml = object.getJSONObject("xml");
        System.out.println(objxml.getString("Event"));
        if(!"subscribe".equals(objxml.getString("Event"))){
            return "";
        }

        String FromUserName = objxml.getString("FromUserName");
        String ToUserName = objxml.getString("ToUserName");
        objxml.put("FromUserName" , ToUserName);
        objxml.put("ToUserName" , FromUserName);
        objxml.put("Content" , "你好");
        return XmlUtil.JsonToXml(object.toString());
    }
}
