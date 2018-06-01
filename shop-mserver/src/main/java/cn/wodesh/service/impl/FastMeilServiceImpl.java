package cn.wodesh.service.impl;

import cn.wodesh.config.AppConfig;
import cn.wodesh.config.FastMeilConfig;
import cn.wodesh.config.ResultInfo;
import cn.wodesh.exception.FinalException;
import cn.wodesh.redis.RedisUtil;
import cn.wodesh.service.IFastMeilService;
import cn.wodesh.util.FastMeil;
import cn.wodesh.util.KeyUtil;
import cn.wodesh.util.ParamValidateUtil;
import cn.wodesh.util.ResultUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by TS on 2018/5/31.
 */
@Service
public class FastMeilServiceImpl implements IFastMeilService{

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Object findFastMeil(String expnumber, String expcode) throws Exception {
        String key = KeyUtil.fastMeil(expnumber , expcode);
        JSONObject obj = null;
        System.out.println(redisUtil.exists(key));
        if(redisUtil.exists(key)){
            String arr = (String) redisUtil.get(key);
            obj = JSONObject.parseObject(arr);
            return ResultUtil.success(obj);
        }
        obj = new JSONObject();
        String res = FastMeil.getOrderTracesByJson(expcode , expnumber);
        ParamValidateUtil.notNull(res , "快递查询返回数据为空");
        JSONObject object = JSONObject.parseObject(res);
        if(!object.getBoolean("Success"))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg("快递查询接口返回失败"));
        JSONArray array = object.getJSONArray("Traces");
        Collections.reverse(array);
        Integer status = object.getInteger("State");
        String ShipperCode = object.getString("ShipperCode");
        obj.fluentPut("list" , array)
                .fluentPut("number" , object.getString("LogisticCode"))
                .fluentPut("name" , FastMeilConfig.FASTMEIL_NAME.get(ShipperCode))
                .fluentPut("status" , status)
                .fluentPut("statusinfo" , FastMeilConfig.FASTMEIL_SATUS.get(status));
        redisUtil.set(key , obj.toJSONString() , AppConfig.FASTMEIL_KEY_OUT_LIMIT);
        return ResultUtil.success(obj);
    }
}
