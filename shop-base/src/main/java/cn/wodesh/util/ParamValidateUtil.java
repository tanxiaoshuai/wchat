package cn.wodesh.util;

import cn.wodesh.config.ResultInfo;
import cn.wodesh.exception.FinalException;

import java.util.List;
import java.util.Map;

/**
 * Created by TS on 2018/2/24.
 */
public class ParamValidateUtil {

    public static void notNull(String param , String msg){

        if(RegexUtil.isNull(param))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void notNull(Integer param , String msg){

        if(RegexUtil.isNull(param))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void IntegrCheck(Integer i , int max , String msg){
        if(i > max)
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void length(int min , long max , String param , String msg){
        long len = param.length();
        if (len < min || len > max)
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void min(int min , Object param, String msg){
        if(param instanceof String)
            if (((String) param).length() < min)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        if(param instanceof Integer)
            if ((Integer)param < min)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        if(param instanceof Long)
            if ((Long)param < min)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void max(int max , Object param, String msg){
        if(param instanceof String)
            if (((String) param).length() > max)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        if(param instanceof Integer)
            if ((Integer)param > max)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        if(param instanceof Long)
            if ((Long)param > max)
                throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void pattern(String param , String reg , String msg){
        if(!RegexUtil.match(param , reg))
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
    }

    public static void notNull(List list , String msg){
        if(list == null || list.size() == 0){
            throw new FinalException(ResultInfo.ERROR_PARAM.setMsg(msg));
        }
    }

}
