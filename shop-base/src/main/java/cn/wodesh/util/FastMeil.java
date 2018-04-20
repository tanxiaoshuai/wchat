package cn.wodesh.util;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.Base64Utils;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
public class FastMeil {
	//电商ID(快递)
	public static final String EBusinessID="1280517";
	//电商加密私钥，快递鸟提供，注意保管，不要泄漏(快递)
	public static final String AppKey="b5719362-fe0a-4fb2-a2be-5698b6c6559d";
	//请求url(快递)
	public static final String ReqURL="http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";

	/**
     * Json方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public static String getOrderTracesByJson(String expCode, String expNo) throws Exception{
		String requestData= new JSONObject().fluentPut("OrderCode" , "").
				fluentPut("ShipperCode" , expCode).fluentPut("LogisticCode" , expNo).toJSONString();
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", URLEncoder.encode(requestData, "UTF-8"));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", "1002");
		String dataSign = Base64Utils.encodeToString(MD5Util.MD5(requestData+AppKey).toLowerCase().getBytes());
		params.put("DataSign", URLEncoder.encode(dataSign, "UTF-8"));
		params.put("DataType", "2");
		String result=HttpClientUtil.postHeader("",new StringBuffer().
				append(ReqURL).append("?").append(getParams(params)).toString(),null );
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
