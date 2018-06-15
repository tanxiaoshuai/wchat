package cn.wodesh.util;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ObjectUtils;
import java.io.IOException;
import java.util.Map;

/**
 * Created by TS on 2017/7/10.
 */
public class HttpClientUtil {
        /**
         * post请求（添加header）
         * @param body
         * @param url
         * @param map
         * @return
         */
        public static String postHeader(String body , String url , Map<String , String> map) throws Exception {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse result;
            if(!ObjectUtils.isEmpty(map)){
                for(String key : map.keySet()){
                    httpPost.setHeader(key, map.get(key));
                }
            }
            StringEntity entity = new StringEntity(body, "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            result = httpClient.execute(httpPost);
            return EntityUtils.toString(result.getEntity(),"utf8");
        }

        /**
         * post请求（添加header）
         * @param body
         * @param url
         * @param map
         * @return
         */
        public static String postHeaderXml(String body , String url , Map<String , String> map) throws Exception {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse result;
            if(!ObjectUtils.isEmpty(map)){
                for(String key : map.keySet()){
                    httpPost.setHeader(key, map.get(key));
                }
            }
            StringEntity entity = new StringEntity(body, "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("text/xml");
            httpPost.setEntity(entity);
            result = httpClient.execute(httpPost);
            return EntityUtils.toString(result.getEntity() , "utf8");
        }

        /**
         * put请求（添加header）
         * @param body
         * @param url
         * @param map
         * @return
         */
        public static String putHeader(String body , String url , Map<String , String> map) throws IOException , NullPointerException {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(url);
            HttpResponse result;
            if(!ObjectUtils.isEmpty(map)) {
                for (String key : map.keySet()) {
                    httpPut.setHeader(key, map.get(key));
                }
            }
            // 解决中文乱码问题
            StringEntity entity = new StringEntity(body, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPut.setEntity(entity);
            result = httpClient.execute(httpPut);
            return EntityUtils.toString(result.getEntity(),"utf8");
        }

        /**
         * get请求(带header)
         * @param url
         * @param map
         * @return
         */
        public static String getHeader(String url , Map<String , String> map) throws IOException , NullPointerException {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse;
            if(!ObjectUtils.isEmpty(map)){
                for(String key : map.keySet()){
                    httpGet.setHeader(key, map.get(key));
                }
            }
            httpResponse = httpClient.execute(httpGet);
            org.apache.http.HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        }

    /**
     * https
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
        public static String getHttps(String url , Map<String , String> map) throws Exception{
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse;
            if(!ObjectUtils.isEmpty(map)){
                for(String key : map.keySet()){
                    httpGet.setHeader(key, map.get(key));
                }
            }
            httpResponse = httpClient.execute(httpGet);
            org.apache.http.HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        }
}
