package cn.wodesh.service.impl;

import cn.wodesh.config.AppConfig;
import cn.wodesh.service.IKeyWordService;
import cn.wodesh.util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by TS on 2018/4/29.
 */
@Service
public class KeyWordServiceImpl implements IKeyWordService{

    @Value("${keywords_api_url}")
    public String keywords_api_url;

    @Override
    public void save(String body) throws Exception {
        HttpClientUtil.postHeader(body , new StringBuffer().append(keywords_api_url).append(AppConfig.KEYWORDS_SAVE).toString() , null);
    }

    @Override
    public Object findByHotSearch() throws Exception {
        String result = HttpClientUtil.getHeader(new StringBuffer().
                append(keywords_api_url).append(AppConfig.KEYWORDS_SEARCH).toString(),null);
        return JSONObject.parseObject(result);
    }
}
