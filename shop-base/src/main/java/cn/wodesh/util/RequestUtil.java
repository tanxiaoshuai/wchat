package cn.wodesh.util;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {

    public static String getHeader(String s){
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getHeader(s);
    }

    public static String getRegion(){
       return  ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getHeader("region");
    }
}
