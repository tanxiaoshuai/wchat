package cn.wodesh.util;

import java.io.BufferedReader;

/**
 * Created by TS on 2018/5/2.
 */
public class IoUtil {

    public static String IoToString(BufferedReader br){
        String s = null;
        StringBuffer buffer = new StringBuffer();
        try {
            while ((s = br.readLine()) != null){
                buffer.append(s);
            }
        }catch (Exception e){
            try {
                br.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
