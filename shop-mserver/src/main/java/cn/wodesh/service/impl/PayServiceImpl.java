package cn.wodesh.service.impl;

import cn.wodesh.service.IPayService;
import cn.wodesh.util.PayUtil;
import cn.wodesh.util.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Created by TS on 2018/5/5.
 */
@Service
public class PayServiceImpl implements IPayService{

    @Autowired
    private PayUtil payUtil;

    @Override
    public Object pay(Map map) throws Exception {
        String paysrt = payUtil.Pay(map.get("openid")+"" ,
                map.get("cash")+"", map.get("orderid")+"" , "1");
        return ResultUtil.success(JSONObject.parseObject(paysrt));
    }

    @Override
    public Object payCallback(String xml) throws Exception {
        return payUtil.callback(xml);
    }
}
