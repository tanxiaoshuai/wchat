package cn.wodesh.util;
import java.io.IOException;
import cn.wodesh.button.Button;
import cn.wodesh.button.Menu;
import cn.wodesh.button.ViewButton;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.util.EntityUtils;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Spring Bean工具类
 *
 * @author Jalena
 * @create 2017-05-08 23:25
 */
public class WeiXinToken {	

	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * get請求
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet doGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(doGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String reuslt = EntityUtils.toString(entity,"utf-8");
			try {
				jsonObject = JSONObject.parseObject(reuslt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}

	/**
	 * POST请求
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) throws Exception{
		DefaultHttpClient client = new DefaultHttpClient();
		client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		return JSONObject.parseObject(result);
	}
	/**
	 * 组装菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		ViewButton button11 = new ViewButton();
		button11.setName("用戶中心");
		button11.setType("view");
		button11.setUrl("http://user.qzone.qq.com/616823670");
		
		ViewButton button21 = new ViewButton();
		button21.setName("我的商城");
		button21.setType("view");
		button21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxace6b87996d2fe13&redirect_uri=http://wodesh.cn/shop-mserver/rest/index&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		
//		ClickButton button31 = new ClickButton();
//		button31.setName("扫码事件");
//		button31.setType("scancode_push");
//		button31.setKey("31");
//		
//		ClickButton button32 = new ClickButton();
//		button32.setName("地理位置");
//		button32.setType("location_select");
//		button32.setKey("32");
		
//		Button button = new Button();
//		button.setName("菜单");
//		button.setSub_button(new Button[]{button31,button32});
		
//		menu.setButton(new Button[]{button11,button21,button});
		menu.setButton(new Button[]{button21,button11});
		return menu;
	}
	
	
	public static int createMenu(String token,String menu) throws Exception{
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInteger("errcode");
		}
		return result;
	}
	
	public static int deleteMenu(String token) throws ParseException, IOException{
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		System.out.println(url);
		JSONObject jsonObject = doGetStr(url);
		int result = 0;
		if(jsonObject != null){
			result = jsonObject.getInteger("errcode");
		}
		return result;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		String token = "8_OSdknwsHOn85YGvD74n02Rkx5SMPLWFpFGI48esz9w-JRtZXxwOQ8fTiaM6XDVUQkP9lTOmQ8_FBbkZoP5Kxk00V6Zhlob3rp2-3q3k2mV7O3OsNmQgLtHL_8k3rQGubg3eJQDplXDw6pk49PYEcAJAWFL";

		int b = createMenu(token, JSONObject.toJSON(initMenu()).toString());
		if(b == 0){
			System.out.println("创建成功！");
		}
		else {
			System.out.println("创建失败！"+b);
		}
//
//		if(deleteMenu(token) == 0){
//			System.out.println("菜单删除SB成功！");
//		}
//		else {
//			System.out.println("菜单删除失败！");
//		}

	}
}
