package newb.c.utilcopy;

import java.io.UnsupportedEncodingException;

import sun.misc.*;

/**
 * Base64加密解密工具类
 * @author woshizbh
 *
 */
@SuppressWarnings("restriction")
public class Base64 {
	/**
	 * Base 加密
	 * @param str
	 * @return
	 */
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	/**
	 *  base64 解密
	 * @param s
	 * @return
	 */
	
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Base64  base64= new Base64();
		String msg = base64.getBase64("cwj,123");
		System.out.println(msg);
		String dMsg=base64.getFromBase64(msg);
		System.out.println(dMsg);
	}
}