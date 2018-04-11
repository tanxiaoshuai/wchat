package cn.wodesh.util;
import java.io.IOException;
import cn.wodesh.bean.Button;
import cn.wodesh.bean.Menu;
import cn.wodesh.bean.ViewButton;
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
	
	//获取token
	public static final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//创建菜单
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	//生产
//	public static final String SC_FW_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx4b2f6604038f2a25&secret=f27811ea6d4a27073e973b7d393e2766&code=Code&grant_type=authorization_code";
	//测试
	public static final String SC_FW_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxace6b87996d2fe13&secret=6e45640b7d6a09c26ec1a4ca34887703&code=Code&grant_type=authorization_code";

	public static String RE_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=Token&openid=Openid&lang=zh_CN&lang=zh_CN";
	//微信跳转主界面 生产
	public static final String RE_BTN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxace6b87996d2fe13&redirect_uri=http://www.wodesh.cn/myWeiXin/token/openid.do&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	//微信跳转主界面 测试
//	public static final String RE_BTN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8475ec94229a2017&redirect_uri=http://txs.viphk.ngrok.org/myWeiXin/token/openid.do&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

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
		String token = "8_tqIBWSDMLOvopb-a4G34K7dLTXR7kJ919W9gGXQhavrkRto_235i7WQV0K0fFJ6JvxH1FEcWJFb5KeVpb1kvBPftaHPtQGvxongTS3IfBEso-koqaUEe_3rBz01xOC8o-i6WSuGDHF-guQ_mSTLiACAXYQ";

		int b = createMenu(token, JSONObject.toJSON(initMenu()).toString());
		if(b == 0){
			System.out.println("创建成功！");
		}
		else {
			System.out.println("创建失败！"+b);
		}
	
//		if(deleteMenu(token) == 0){
//			System.out.println("菜单删除SB成功！");
//		}
//		else {
//			System.out.println("菜单删除失败！");
//		}

	}
}
