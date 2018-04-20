package cn.wodesh.util;
import cn.wodesh.config.AppConfig;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.Base64Utils;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
public class FastMeil {

	/**
     * Json方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public static String getOrderTracesByJson(String expCode, String expNo) throws Exception{
		String requestData= new JSONObject().fluentPut("OrderCode" , "").
				fluentPut("ShipperCode" , expCode).fluentPut("LogisticCode" , expNo).toJSONString();
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", URLEncoder.encode(requestData, "UTF-8"));
		params.put("EBusinessID", AppConfig.EBUSINESSID);
		params.put("RequestType", "1002");
		String dataSign = Base64Utils.encodeToString(MD5Util.MD5(new StringBuffer().
				append(requestData).append(AppConfig.APPKEY).toString()).toLowerCase().getBytes());
		params.put("DataSign", URLEncoder.encode(dataSign, "UTF-8"));
		params.put("DataType", "2");
		String result=HttpClientUtil.postHeader("",new StringBuffer().
				append(AppConfig.FAST_MAILE_URL).append("?").append(getParams(params)).toString(),null );
		return result;
	}

	public static String getParams(Map<String , String> params){
		StringBuilder param = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if(param.length()>0){
				param.append("&");
			}
			param.append(entry.getKey());
			param.append("=");
			param.append(entry.getValue());
		}
		return param.toString();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getOrderTracesByJson("YTO" , "812878059023"));
	}
}
