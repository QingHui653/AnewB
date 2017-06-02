package com.inmsg.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class StringUtil {
	public static final String EMPTY_STRING = "";

	private final static String[] hexDigits = {
	      "0", "1", "2", "3", "4", "5", "6", "7",
	      "8", "9", "A", "B", "C", "D", "E", "F"};

	/**��  ��: Object
	 * ����ֵ: �ַ���
	 * */
	public static String getStringValueFromObject(Object obj) {
		if (null == obj) {
			return "";
		} else {
			return obj.toString();
		}
	}
	
	public static String Hex2String(String hexstr,String charsetName) {
		byte bytestr[];
		try {
			bytestr = Hex.decodeHex(hexstr.toCharArray());
			return new String(bytestr,charsetName);
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static String String2Hex(String hexstr,String charsetName) {
		try {
			char charshex[] = Hex.encodeHex(hexstr.getBytes(charsetName));
			return new String(charshex).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	
	  /**
	   * ת���ֽ�����Ϊ16�����ִ�
	   * @param b �ֽ�����
	   * @return 16�����ִ�
	   */

	  public static String byteArrayToHexString(byte[] b) {
	    StringBuffer resultSb = new StringBuffer();
	    for (int i = 0; i < b.length; i++) {
	      resultSb.append(byteToHexString(b[i]));
	    }
	    return resultSb.toString();
	  }

	  private static String byteToHexString(byte b) {
	    int n = b;
	    if (n < 0)
	      n = 256 + n;
	    int d1 = n / 16;
	    int d2 = n % 16;
	    return hexDigits[d1] + hexDigits[d2];
	  }

	  public static String MD5Encode(String origin) {
	    String resultString = null;

	    try {
	      resultString=new String(origin);
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
	    }
	    catch (Exception ex) {

	    }
	    return resultString;
	  }

	  public static String string2HexString(String str, String charsetName) {
		  StringBuffer strBuff = new StringBuffer(100);
		  String stmp = null;
		  if (str == null) {
		   return null;
		  }
		 
		  try {
		   byte[] strByte = str.getBytes(charsetName);
		   for (int i = 0; i < strByte.length; i++) {
		    stmp = Integer.toHexString(strByte[i] & 0xFF);
		    if (stmp.length() == 1) {
		     strBuff.append("0");
		    }
		    strBuff.append(stmp);
		   }
		  } catch (Throwable ex) {
		   ex.printStackTrace();
		  }
		  return strBuff.toString().trim().toUpperCase();
		 }
		 
		 /**
		  * �õ�һ�����������ʮ�����Ƶ��ַ�����ָ�������ԭʼ�ַ���
		  * 
		  * @param hexStr
		  *            String Ҫ������ַ���
		  * @param charsetName
		  *            String ������ַ���
		  * @return String
		  */
		 public static String getOrigValueofHexString(String hexStr,
		   String charsetName) {
		  String result = null;
		  if (hexStr == null || hexStr.length() < 1) {
		   return null;
		  }
		 

		   if (hexStr.length() % 2 != 0) {
		    throw new IllegalArgumentException();
		   }
		   char[] arr = hexStr.toCharArray();
		   byte[] b = new byte[hexStr.length() / 2];
		   for (int i = 0, j = 0, l = hexStr.length(); i < l; i++, j++) {
		    String swap = "" + arr[i++] + arr[i];
		    int byteint = Integer.parseInt(swap, 16) & 0xFF;
		    b[j] = new Integer(byteint).byteValue();
		   }
		 
		   try {
			result = new String(b, charsetName);
		   } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  } finally {
			   return result;
		  }

		 }

	public static BigDecimal String2Decimal(String str) {
		BigDecimal returnvalue;
		if (str == null || str.length() == 0) {
			returnvalue = new BigDecimal(0.0);
		}
		try {
			returnvalue = new BigDecimal(str);
		}catch(Exception e) {
			returnvalue =  new BigDecimal(0.0);
		}

		return returnvalue;
	}
	
	public static long String2Long(String str) {
		Long returnvalue;
		if (str == null || str.length() == 0) {
			returnvalue = new Long(0);
		}
		
		try {
			returnvalue = new Long(str);
		}catch(Exception e) {
			returnvalue =  new Long(0);
		}

		return returnvalue;
	}
	
    /**
     * ����ת����ISO8859_1 �� GB2312
     * 
     * @param stringValue
     *            ISO String
     * @return GB2312 String
     */
    public static String iso2gb ( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "ISO8859_1" ) ,
                    "GB2312" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }

    /**
     * ����ת���� utf �� iso
     * 
     * @param stringValue
     *            UTF8 String
     * @return GB2312 String
     */
    public static String utf2iso ( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "UTF-8" ) , "ISO-8859-1" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }
    /**
     * ����ת����ISO8859_1 �� utf
     * 
     * @param stringValue
     *            ISO8859_1 String
     * @return GBK String
     */
    public static String iso2utf( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "ISO8859_1" ) , "UTF-8" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }
    /**
     * ����ת����GB2312��ISO8859_1
     * 
     * @param stringValue
     *            GB2312 String
     * @return ISO String
     */
    public static String gb2iso ( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "gb2312" ) ,
                    "ISO-8859-1" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }

    /**
     * ����ת���� UTF8 �� GB2312
     * 
     * @param stringValue
     *            UTF8 String
     * @return GB2312 String
     */
    public static String utf2gb ( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "UTF-8" ) , "GB2312" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }
    
    /**
     * ����ת���� UTF8 �� GBK
     * 
     * @param stringValue
     *            UTF8 String
     * @return GBK String
     */
    public static String utf2gbk ( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "UTF-8" ) , "GBK" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }

    /**
     * ����ת����GB2312 �� UTF8
     * 
     * @param stringValue
     *            GB2312 String
     * @return UTF8 String
     */
    public static String gb2utf ( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "GB2312" ) , "UTF-8" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }
    
    /**
     * ����ת����GBK �� UTF8
     * 
     * @param stringValue
     *            GBK String
     * @return UTF8 String
     */
    public static String gbk2utf ( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "GBK" ) , "UTF-8" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }

    /**
     * ����ת����ISO8859_1 �� GBK
     * 
     * @param stringValue
     *            ISO8859_1 String
     * @return GBK String
     */
    public static String iso2gbk ( String stringValue )
    {
        // �������Ϊnull������null
        if ( stringValue == null )
        {
            return null ;
        }
        String value = null ;
        try
        {
            value = new String ( stringValue.getBytes ( "ISO8859_1" ) , "GBK" ) ;
        } catch ( UnsupportedEncodingException ex )
        {
            value = stringValue ;
        }
        return value ;
    }

    /**
     * �ж��Ƿ�ָ���ַ���Ϊ���ַ���(null���߳���Ϊ0)
     * 
     * @param stringValue
     *            String
     * @return boolean ���򷵻�true�����򷵻�false
     */
    public static boolean isEmptyString ( String stringValue )
    {
        if ( stringValue == null || stringValue.trim().length ( ) < 1 )
        {
            return true ;
        } else
        {
            return false ;
        }
    }

    /**
     * �ַ���ת��������
     * 
     * @param stringValue
     *            String �ַ���
     * @return int ����
     */
    public static int stringToInt ( String stringValue )
    {
        return stringToInt ( stringValue , - 1 ) ;
    }

    /**
     * �ַ���ת��������
     * 
     * @param stringValue
     *            String �ַ���
     * @param defaultValue
     *            ת��ʧ��ʱ��Ĭ��ֵ
     * @return int ����
     */
    public static int stringToInt ( String stringValue , int defaultValue )
    {
        int intValue = defaultValue ;
        if ( stringValue != null )
        {
            try
            {
                intValue = Integer.parseInt ( stringValue ) ;
            } catch ( NumberFormatException ex )
            {
                intValue = defaultValue ;
            }
        }
        return intValue ;
    }

    /**
     * ����ת�����ַ���
     * 
     * @param stringValue
     *            String �ַ���
     *            length �ܳ���
     *            d ռλ��
     * @param defaultValue
     *            ת��ʧ��ʱ��Ĭ��ֵ
     * @return int ����
     */
    public static String intToStr (int intvalue,int length,char d) {
    	String svalue = String.valueOf(intvalue);
    	StringBuffer sb = new StringBuffer();

    	for (int i=0;i < (length - svalue.length());i++) {
    		sb.append(d);
    	}
    	sb.append(svalue);
    	return sb.toString();
    }

    /**
     * �����ַ����ͳ��ȣ��ж��Ƿ񳬳�������ȣ���Ӣ�Ķ���һ���ַ���
     * 
     * @param str
     *            �ַ���
     * @param length
     *            �ƶ��ĳ���
     * @return boolean �Ƿ񳬳����ȣ�����Ϊtrue ����Ϊfalses
     */
    public static boolean charLength ( String str , int length )
    {

        char a = 'a' ;
        char z = 'z' ;
        char A = 'A' ;
        char Z = 'Z' ;
        ArrayList letter = new ArrayList ( ) ;
        ArrayList chinese = new ArrayList ( ) ;
        char [ ] c = str.toCharArray ( ) ;
        for ( int i = 0 ; i < c.length ; i ++ )
        {
            if ( c [ i ] >= a || c [ i ] <= z )
            {
                letter.add ( String.valueOf ( c [ i ] ) ) ;
            } else if ( c [ i ] >= A || c [ i ] <= Z )
            {
                letter.add ( String.valueOf ( c [ i ] ) ) ;
            } else
            {
                chinese.add ( String.valueOf ( c [ i ] ) ) ;
            }
        }
        int sum = letter.size ( ) + chinese.size ( ) ;
        if ( sum <= length )
        {
            return true ;
        } else
        {
            return false ;
        }

    }

    /**
     * ���ַ������� BASE64 ����
     * 
     * @param s
     *            ����ǰ���ַ���
     * @return String �������ַ���
     */
    public static String base64Encode ( String s )
    {
        if ( s == null )
            return null ;
        return new String(Base64.encodeBase64(s.getBytes()));
    }

    /**
     * �� BASE64 ������ַ��� s ���н���
     * 
     * @param s
     *            BASE64 ������ַ���
     * @return String �������ַ���
     */
    public static String base64Decode ( String s )
    {
        if ( s == null )
            return null ;
        return new String(Base64.decodeBase64(s.getBytes()));
    }

    /**
     * ����ָ�����ȵ�����ַ���
     * 
     * @param len
     *            ָ��Ҫ���ɵ��ַ����ĳ���
     * @return String ����ַ���
     */
    public static String randStr ( int len )
    {
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
        String randStr = "" ;
        Random random = new Random ( ) ;

        for ( int i = 0 ; i < len ; i ++ )
        {
            int c = random.nextInt ( 62 ) ;
            randStr += str.substring ( c , c + 1 ) ;

        }

        return randStr ;
    }

    /**
     * object ת���� String ��������null�ļ���Լ��ո�Ĺ���
     * 
     * @param object
     *            Ҫת����object
     * @return String ת������ַ���
     */
    public static String toStr ( Object object )
    {
        String result = "" ;
        if ( object == null )
            return "" ;
        result = ( String ) object ;
        result = result.trim ( ) ;

        return result ;
    }

    /**
     * ��objectת�����ַ�����Ȼ����˵�ָ�����ַ�
     * 
     * @param object
     *            Ҫת�����ַ���
     * @param filter
     *            Ҫ���˵�����
     * @return String ת�����˺���ַ���
     */
    public static String clearStr ( Object object , String filter )
    {
        String result = "" ;
        if ( object == null )
            return "" ;
        result = ( String ) object ;
        result = result.trim ( ) ;
        result = result.replaceAll ( filter , "" ) ;
        return result ;
    }
    
    /***�ж��ַ����Ƿ�ȫ����***
     * 
     * **/
	public static boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
		return true;
	}
	
	/**
	 * ����ָ���������ָ���ַ��������
	 * */
	public static String leftFillStr (int len,String oldStr,char fillchar) {
		if (len <= oldStr.length()) {
			return oldStr;
		}

		StringBuffer sb = new StringBuffer();

		for (int i = 0;i <len - oldStr.length();i++) {
			sb.append(fillchar);
		}
		sb.append(oldStr);

		return sb.toString();
	}
	
	
	//�����null�ַ�����Ҳ��Ϊ�ǿ��ַ���
	public static String Object2Str(Object obj) {
		if (obj != null && !obj.toString().equalsIgnoreCase("null")) {
			return obj.toString().trim();
		}

		return "";
	}
	
	
	/**
	 * �滻\nΪ<br>
	 * 
	 * @param str
	 *            Ҫ��ʽ�����ַ���
	 * @return ��ʽ������ַ���
	 */
	public static String toBr(String str) {
		String html = str;
		html = replace(html, "\r\n", "\n");
		html = replace(html, "\n", "<br>\n");
		html = replace(html, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		html = replace(html, "\\", "\\\\");
		html = replace(html, "\'", "\\\'");
		html = replace(html, "\"", "\\\"");
		html = replace(html, " ", "&nbsp;&nbsp;"); // ����Ӣ�Ŀո�

		return html;
	}
	
	
	/*
	 * ��Str�е��ַ���str1�滻Ϊstr2
	 */
	public static String replace(String Str, String str1, String str2) {
		String temp = Str;
		int flag = 0;
		if (Str.indexOf(str1) != -1) {
			int str1Length = str1.length();
			boolean bflag = false;
			String str3 = "";
			if (str2.indexOf(str1) != -1) {
				bflag = true;
				str3 = str2;
				str2 = "!#@$%";
			}
			while (temp.indexOf(str1) != -1) {
				flag = temp.indexOf(str1);
				temp = temp.substring(0, flag) + str2
						+ temp.substring(flag + str1Length);
			}
			if (bflag) {
				str1Length = str2.length();
				while (temp.indexOf(str2) != -1) {
					flag = temp.indexOf(str2);
					temp = temp.substring(0, flag) + str3
							+ temp.substring(flag + str1Length);
				}
			}
		}
		return temp;
	}
	
	
	/**
	 * ��ĳһ���ַ��ָ���ַ�����ת����list
	 * @param str Ҫ�ָ���ַ���
	 * @param split �ָ���
	 * @return
	 */
	public static List<String> str2list(String str,String split){
		List<String> list=new ArrayList<String>();
		if(str.indexOf(split)>0){
			String[] strs=str.split(",");
			for(int i=0;i<strs.length;i++){
				list.add(strs[i].trim());
			}
		}else{
			list.add(str);
		}
		
		return list;
	}
	
	public static void main(String[] args){
		System.out.println(StringUtil.str2list("1,2,3",","));
	}


}
