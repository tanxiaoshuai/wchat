package cn.wodesh.util;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Java表单验证工具类
 *
 * Created by TS on 2018/2/23.
 *
 */
@SuppressWarnings("unchecked")
public class RegexUtil {

    public final static boolean isNull(Object[] objs){
        if(objs==null||objs.length==0) return true;
        return false;
    }

    public final static boolean isNull(Integer integer){
        if(integer==null) return true;
        return false;
    }

    public final static boolean isNull(Collection collection){
        if(collection==null||collection.size()==0) return true;
        return false;
    }

    public final static boolean isNull(Map map){
        if(map==null||map.size()==0) return true;
        return false;
    }

    public final static boolean isNull(String str){
        return str == null || "".equals(str.trim()) || "null".equals(str.toLowerCase());
    }


    public final static boolean isNull(Long longs){
        if(longs==null||longs==0) return true;
        return false;
    }

    public final static boolean isNotNull(Long longs){
        return !isNull(longs);
    }

    public final static boolean isNotNull(String str){
        return !isNull(str);
    }

    public final static boolean isNotNull(Collection collection){
        return !isNull(collection);
    }

    public final static boolean isNotNull(Map map){
        return !isNull(map);
    }

    public final static boolean isNotNull(Integer integer){
        return !isNull(integer);
    }

    public final static boolean isNotNull(Object[] objs){
        return !isNull(objs);
    }

    /**
     * 正则表达式匹配
     *
     * @param text 待匹配的文本
     * @param reg 正则表达式
     * @return
     * Created by TS on 2018/2/23.
     */
    public final static boolean match(String text, String reg) {
        if (isNull(text) || isNull(reg))
        return false;
        return Pattern.compile(reg).matcher(text).matches();
    }
}

