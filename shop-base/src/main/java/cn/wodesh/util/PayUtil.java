package cn.wodesh.util;


import cn.wodesh.config.ResultInfo;
import cn.wodesh.config.WchatConfig;
import cn.wodesh.exception.FinalException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Created by TS on 2018/5/4.
 */
@Component
public class PayUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayUtil.class);

    @Value("${default.pay_callback}")
    private String pay_callback;

    public String Pay(String openId , String cash , String orderId , String paytype) throws Exception{
        JSONObject object = new JSONObject();
        object.put("appid", WchatConfig.APPID);
        object.put("mch_id", WchatConfig.MCH_ID);
        object.put("nonce_str", KeyUtil.uuid());
        object.put("body", WchatConfig.PAY_SHOP_NAME);
        object.put("out_trade_no", orderId);
        object.put("total_fee", cash);
        object.put("spbill_create_ip", "127.0.0.1");
        object.put("notify_url", pay_callback);
        object.put("trade_type", "JSAPI");
        object.put("openid", openId);
        object.put("attach" , paytype);
        object.put("sign" , paySign(object));
        String xml = getXmlToCDATA(object);
        String res = HttpClientUtil.postHeaderXml(xml , WchatConfig.PAY_URL , null);
        JSONObject reso = JSONObject.parseObject(XML.toJSONObject(res).toString())
                .getJSONObject("xml");
        LOGGER.info("微信支付生成预付订单返回参数：{}" , reso);
        if ("FAIL".equals(reso.getString("return_code")))
            throw new FinalException(ResultInfo.PAY_ORDER_ERROR);
        String sign = reso.getString("sign");
        reso.remove("sign");
        if(!sign.equals(paySign(reso)))
            throw new FinalException(ResultInfo.PAY_ORDER_SIGN_ERROR);
        String prepay_id = reso.getString("prepay_id");
        return DataToAjax(WchatConfig.APPID , prepay_id).toString();
    }

    public String paramstr(JSONObject object){
        List<String> list = new ArrayList();
        for(String key : object.keySet())
            list.add(new StringBuffer().append(key).
                    append("=").
                    append(object.get(key)).toString());
        String[] arr =  list.toArray(new String[list.size()]);
        Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
        StringBuffer param = new StringBuffer();
        for(int i = 0 ; i < arr.length ; i++)
            param.append(arr[i]).append("&");
        return param.substring(0,param.length() - 1);
    }

    /**
     * 将map集合转化为xml格式的字符串
     * @param parameters
     * @return
     */
    public String getXmlToCDATA(JSONObject parameters) throws Exception {
        for(String key : parameters.keySet()){
            if ("attach".equalsIgnoreCase(key)||
                    "body".equalsIgnoreCase(key)||
                    "sign".equalsIgnoreCase(key)||
                    "attach".equalsIgnoreCase(key))
                parameters.put(key , new StringBuffer().
                        append("<![CDATA[").
                        append(parameters.get(key)).append("]]>"));
        }
        return XmlUtil.JsonToXml(parameters.toString() , "xml")
                .replace("&lt;" , "<")
                .replace("&gt;" , ">");
    }

    /**
     * 生成签名串
     * @param object
     * @return
     */
    public String paySign(JSONObject object){
        return MD5Util.MD5(new StringBuffer().
                append(paramstr(object)).append("&key=").
                append(WchatConfig.PAY_KEY).toString());
    }

    public JSONObject DataToAjax(String appid , String prepay_id){
        JSONObject o = new JSONObject()
            .fluentPut("appId" , appid)
            .fluentPut("package" , new StringBuffer().append("prepay_id=").append(prepay_id))
            .fluentPut("timeStamp" , System.currentTimeMillis())
            .fluentPut("nonceStr" , KeyUtil.uuid())
            .fluentPut("signType" , "MD5");
            o.put("paySign" , paySign(o));
        return o;
    }

    public String callback(String xml){
        JSONObject body = JSONObject.parseObject(XML.toJSONObject(xml).toString());
        body = body.getJSONObject("xml");
        System.out.println("支付回调：" + body);
        String sign = body.getString("sign");
        body.remove("sign");
        String sign_ = paySign(body);
        if(!sign_.equals(sign))
            throw new FinalException(ResultInfo.PAY_CALLBACK_ERROR);
        return WchatConfig.PAY_CALLBACK_RETURN_MSG;
    }

    public String paySearchOrderId(String orderid) throws Exception {
        JSONObject object = new JSONObject();
        object.fluentPut("appid" , WchatConfig.APPID)
                .fluentPut("mch_id" , WchatConfig.MCH_ID)
                .fluentPut("out_trade_no" , orderid)
                .fluentPut("nonce_str" , KeyUtil.uuid())
                .fluentPut("sign_type" , "MD5");
        object.put("sign" , paySign(object));
        String xml = XmlUtil.JsonToXml(object.toString() , "xml");
        String res = HttpClientUtil.postHeaderXml(xml , WchatConfig.PAY_ORDER_SEARCH , null);
        JSONObject obj = JSONObject.parseObject(XML.toJSONObject(res).toString())
                .getJSONObject("xml");
        LOGGER.info("微信支付订单查询：{}" , obj);
        return obj.toString();
    }

    public static void main(String[] args) throws Exception {
        new PayUtil().paySearchOrderId("1163a0b942064ec6a5e8375dc9a5e48a");
    }

}
