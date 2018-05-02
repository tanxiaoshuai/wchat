package cn.wodesh.util;

import java.security.MessageDigest;
import java.util.Arrays;


public class WchatChackUtil {
	
	public static final String ACC_TOKEN = "tanxiaoshuai";
	
	public static boolean chackSigner(String signature,String timestamp,String nonce) throws Exception {
		String[] arr = new String[3];
		for(int i=0;i<arr.length;i++){
			arr[i] = "";
        }
		arr[0] = (ACC_TOKEN);
        arr[1] = (timestamp);
        arr[2] = (nonce);
        Arrays.sort(arr);
		 StringBuffer cBuffer = new StringBuffer();
		 for(int i = 0;i<arr.length;i++){
			 cBuffer.append(arr[i]);
		 }
		String sign = getSha1(new String(cBuffer));
		return signature.equals(sign) ? true : false;
	}
	
	
    public static String getSha1(String str) throws Exception{
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
        mdTemp.update(str.getBytes("UTF-8"));
        byte[] md = mdTemp.digest();
        int j = md.length;
        char buf[] = new char[j*2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(buf);
    }

}
