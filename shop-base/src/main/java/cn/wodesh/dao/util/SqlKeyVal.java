package cn.wodesh.dao.util;

/**
 * Created by TS on 2018/4/14.
 */
public class SqlKeyVal {

    public static String field(String column , String val){
        return new StringBuffer()
                .append(" ").append(column)
                .append(" = ").append("'")
                .append(val).append("'").toString();
    }
}
