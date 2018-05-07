package cn.wodesh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public final static String YEARTOSS = "yyyy-MM-dd HH:mm:ss";

    public static String longForTime(long s , String mat){
        SimpleDateFormat format = new SimpleDateFormat(mat);
        Date date = new Date(s);
        return format.format(date).toString();
    }

    public static String currentTime(String mat){
        SimpleDateFormat format = new SimpleDateFormat(mat);
        Date date = new Date(System.currentTimeMillis());
        return format.format(date).toString();
    }

    /**
     * 将时间模板   转化为毫秒"yyyy-MM-dd HH:mm:ss"
     * @param time
     * @return
     */
    public static long longForDate(String time , String formatStr){
        SimpleDateFormat format =  new SimpleDateFormat(formatStr);
        long dateL = 0;
        try {
            Date date = format.parse(time);
            dateL = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateL;
    }
}
