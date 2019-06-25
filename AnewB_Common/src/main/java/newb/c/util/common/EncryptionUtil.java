package newb.c.util.common;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
public class EncryptionUtil {
	
	/**
	 * 使用commons base64加密
	 * @param data
	 * @return
	 */
	public static String base64Encode(String data) {
		return Base64.encodeBase64String(data.getBytes());
	}
	
	/**
	 * 使用commons base64 解密 UTF-8
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Decode(String data) throws UnsupportedEncodingException {
		return new String(Base64.decodeBase64(data),"UTF-8");
	}
	/**
	 * MD5加密 无法解密
	 * @param data
	 * @return
	 */
	public static String  md5Hex(String data) {
		return DigestUtils.md5Hex(data);
	}
	
	/**
     * sha512加密
     * */
    public static String sha512Hex(String data){
        return DigestUtils.sha512Hex(data);
    }
     
    /**
     * sha256加密
     * */
    public static String sha256Hex(String data){
        return DigestUtils.sha256Hex(data);
    }
}
