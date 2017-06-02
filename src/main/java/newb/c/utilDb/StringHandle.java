package newb.c.utilDb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * �ַ����� ����java.lang.String��û�з���
 */
public class StringHandle {
	/**
	 * Example: 80005 == 80006 80009 == 80010;
	 */
	public static String increaseNumber(String oldStr) {
		int iMaxId = Integer.parseInt(oldStr);
		iMaxId++;
		String sMaxId = String.valueOf(iMaxId);
		while (sMaxId.length() < oldStr.length()) {
			sMaxId = "0" + sMaxId;
		}
		return sMaxId;
	}
	public static void main(String[] args) {
		System.out.println(StringHandle.increaseNumber("00000010"));
	}

	/**
	 * ���ַ��ַ�ʽ���� Example: 0 -> 1 9 -> A A -> B Z -> A0
	 * 
	 * 00 -> 01 09 -> 10
	 * 
	 * @param oldStr
	 * @return
	 */
	public static String increaseByLetter(String oldStr) {
		char ch = oldStr.charAt(oldStr.length() - 1);
		char temp = (char) ((int) ch + 1);
		if (ch == '9') {
			// ������9
			if (oldStr.length() - 1 > 0) {
				char upCh = oldStr.charAt(oldStr.length() - 2);
				if (upCh >= 'A' && upCh <= 'Z') {
					return oldStr.substring(0, oldStr.length() - 1) + "A";
				} else {
					return increaseByLetter(oldStr.substring(0,
							oldStr.length() - 1))
							+ "0";
				}
			} else {
				return "A";
			}
		} else if (ch == 'Z') {
			if (oldStr.length() - 1 > 0) {
				return increaseByLetter(oldStr
						.substring(0, oldStr.length() - 1))
						+ "0";
			} else {
				return "A" + "0";
			}
		} else {
			return oldStr.substring(0, oldStr.length() - 1)
					+ String.valueOf(temp);
		}
	}

	/**
	 * valueStr����null,��ô���ر��� ���򷵻ؿմ�""
	 */
	public static String nullToString(String valueStr) {
		if (valueStr == null)
			return "";
		else
			return valueStr;
	}

	/*
	 * (1)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/*
	 * charIsChinese��isChinese��
	 */
	/**
	 * �ж��ַ��Ƿ��Ǻ��� judge the character is chinese character or other character<BR>
	 * Example: <li>c is a~z && A~Z && 0~9 and special character like '#','$'
	 * etc return false<BR> <li>c is chinese like '��','��' etc,this is two
	 * character then return true
	 * 
	 * @param c
	 *            is the chinese character
	 * @return true if c is chinese character,else return false
	 */
	public static boolean charIsChinese(char c) {
		return c > 255;
	}

	/**
	 * �õ����к��ֵ��ַ���ֽڳ���?return string'length,this string maybe include Chinese
	 * character <li>English character take 1 position <li>Chinese character
	 * take 2 position
	 */
	public static int chineseLength(String str) {
		String s = null;
		char[] arr = null;
		int i, l;
		s = str;
		if (s == null) {
			return 0;
		}
		arr = s.toCharArray();
		l = 0;
		for (i = 0; i <= arr.length - 1; i++) {
			l += charIsChinese(arr[i]) ? 2 : 1;
		}
		return l;
	}

	/**
	 * �ַ����Ƿ�����ַ�?������һ����return true
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean isChinese(String str) {
		String s = null;
		char[] arr = null;
		int i;
		s = str;
		if (s == null) {
			return true;
		}
		arr = s.toCharArray();
		for (i = 0; i <= arr.length - 1; i++) {
			if (charIsChinese(arr[i])) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (2)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/* contains */
	/**
	 * �ж��ַ����Ƿ��ָ�����Ӵ�?check whether the string contains the substring,default
	 * match Upper and Lower case
	 * 
	 * @param s
	 *            String
	 * @param sub
	 *            String
	 * @return boolean
	 */
	public static boolean contains(String s, String sub) {
		return contains(s, sub, true);
	}

	/**
	 * �ж��ַ����Ƿ��ָ�����Ӵ�?check whether the string contains the substring
	 * 
	 * @param s
	 *            String
	 * @param sub
	 *            String
	 * @param ignoreCase
	 *            is judge the character is Case or not Case
	 * @return boolean
	 */
	public static boolean contains(String s, String sub, boolean ignoreCase) {
		if (s == null || sub == null) {
			return false;
		}
		String s1 = ignoreCase ? s.toUpperCase() : s;
		String sub1 = ignoreCase ? sub.toUpperCase() : sub;
		return s1.indexOf(sub1) >= 0;
	}

	/*
	 * (3)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ����ַ��а��ƶ��Ӵ�������?get the count of substring in string s
	 */
	public static int countSubstring(String s, String sub) {
		return countSubstring(s, sub, true);
	}

	/**
	 * ����ַ��а��ƶ��Ӵ�������?get the count of substring in string s
	 */
	public static int countSubstring(String s, String sub, boolean ignoreCase) {
		if (s == null || s.equals("") || sub == null || sub.equals("")) {
			return 0;
		}
		String t = replace(s, sub, "", !ignoreCase);
		return (s.length() - t.length()) / sub.length();
	}

	/*
	 * (4)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ���е���ߵ��ַ��õ��ұ�n���ַ� cut string to specified length(from left)
	 * cutString("word", 3) --> "ord"
	 */
	public static String cutStringLeft(String s, int n) {
		String str = s == null ? "" : s;
		int place = str.length() - (n < 0 ? 0 : n);
		str = str.substring(place);
		return str;
	}

	/**
	 * ���е��ұߵ��ַ��õ����n���ַ� cut string to specified length(from right)
	 * cutString("word", 3) --> "wor"
	 */
	public static String cutStringRight(String s, int n) {
		String str = s == null ? "" : s;
		int place = n <= str.length() ? (n >= 0 ? n : 0) : str.length();
		str = str.substring(0, place);
		return str;
	}

	/**
	 * ��Str1������ַ�ȫ���е�?Example: str1="010000",str2="00" => "01"
	 */
	public static String cutStringRight(String str1, String str2) {
		String tempStr1 = (str1 == null ? "" : str1);
		String tempStr2 = (str2 == null ? "" : str2);

		while (tempStr1.substring(tempStr1.length() - tempStr2.length(),
				tempStr1.length()).equals(str2)) {
			tempStr1 = tempStr1.substring(0, tempStr1.length()
					- tempStr2.length());
		}
		return tempStr1;
	}

	/**
	 * ��Str1������ַ�ȫ���е�?Example: str1="010000",str2="01" => "0000"
	 */
	public static String cutStringLeft(String str1, String str2) {
		String tempStr1 = (str1 == null ? "" : str1);
		String tempStr2 = (str2 == null ? "" : str2);

		if (tempStr1.substring(0, tempStr2.length()).equals(str2)) {
			tempStr1 = tempStr1.substring(tempStr2.length());
		}
		return tempStr1;
	}

	/*
	 * (5)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ����ֵת��Ϊ���ַ�(ע������Ĳ���Ҫʵ��toString����)
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static String nullToEmpty(Object obj) {
		return nullToEmpty(obj.toString());
	}

	/**
	 * ����ֵת��Ϊ���ַ�
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String nullToEmpty(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * �ж��Ƿ��� ����
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * �����ַ�ת��Ϊ��ֵ(ע������Ĳ���Ҫʵ��toString����)
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static String emptyToNull(Object obj) {
		return emptyToNull(obj.toString());
	}

	/**
	 * �����ַ�ת��Ϊ��ֵ
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String emptyToNull(String str) {
		if (str != null && str.trim().equals("")) {
			return null;
		}
		return str;
	}

	/**
	 * ��"null"�ַ�ת��Ϊ��ֵ
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String nullString(String s) {
		return s == null || s.equalsIgnoreCase("null")  || s.equalsIgnoreCase("NULL") ? null : s;
	}

	/**
	 * ���ַ�ȥ��ǰ��ո�����ַ�Ϊnull,��ת��Ϊ�մ�""
	 * 
	 * @param str
	 *            Object
	 * @return String
	 */
	public static String nullToEmpty_trim(Object str) {
		if (str == null || str.equals("null")) {
			return "";
		}
		return nullToEmpty_trim(str.toString());
	}

	/**
	 * ���ַ�ȥ��ǰ��ո�����ַ�Ϊnull,��ת��Ϊ�մ�""
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String nullToEmpty_trim(String str) {
		if (str == null) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * �Ƿ��ǿ��ַ���ֵ
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.equals("") || str.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	/*
	 * (7)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * �ж������ַ��Ƿ���ͬ������ִ�С�?
	 * 
	 * @param s1
	 *            String
	 * @param s2
	 *            String
	 * @return boolean
	 */
	public static boolean equalsIgnoreCase(String s1, String s2) {
		return equals(s1, s2, true);
	}

	/**
	 * �����ַ�ȥ��ǰ��ո�����ַ�Ϊnull,��ת��Ϊ�մ�"",֮���ٱȽ����Ƿ���ͬ,������ִ�С�?
	 * 
	 * @param s1
	 *            String
	 * @param s2
	 *            String
	 * @return boolean
	 */
	public static boolean equalsIgnoreCase_trim(String s1, String s2) {
		return nullToEmpty_trim(s1).equalsIgnoreCase(nullToEmpty_trim(s2));
	}

	/**
	 * �ж������ַ��Ƿ���ͬ
	 * 
	 * @param s1
	 *            String
	 * @param s2
	 *            String
	 * @return boolean
	 */
	public static boolean equals(String s1, String s2) {
		return equals(s1, s2, true);
	}

	/**
	 * �����ַ�ȥ��ǰ��ո�����ַ�Ϊnull,��ת��Ϊ�մ�"",֮���ٱȽ����Ƿ���ͬ<BR>
	 * �������һ����?",����һ��Ϊnull,��ô��������Ҳ��Ϊ����ͬ��
	 * 
	 * @param s1
	 *            String
	 * @param s2
	 *            String
	 * @return boolean
	 */
	public static boolean equals_trim(String s1, String s2) {
		return nullToEmpty_trim(s1).equals(nullToEmpty_trim(s2));
	}

	/**
	 * �ж������ַ��Ƿ���ͬ
	 * 
	 * @param s1
	 *            String
	 * @param s2
	 *            String
	 * @param ignoreCase
	 *            boolean �Ƿ���ִ�С�?
	 * @return boolean
	 */
	public static boolean equals(String s1, String s2, boolean ignoreCase) {
		if (s1 == null) {
			return s2 == null;
		} else {
			if (s2 != null) {
				s1 = ignoreCase ? s1.toLowerCase() : s1;
				s2 = ignoreCase ? s2.toLowerCase() : s2;
				return s1.equals(s2);
			} else {
				return false;
			}
		}
	}

	/*
	 * (8)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ���ַ���ұ�����ַ�,����n������ StringHandle.fixLengthLeft("ABCDEFG",'Y',10)
	 * =>ABCDEFGYYY fix the string' length(from left)
	 */
	public static String fixLengthLeft(String s, char c, int n) {
		return fixLengthLeft(s, String.valueOf(c), n);
	}

	/**
	 * ���ַ���ұ�����ַ�,����n������ fix the string' length(from left)
	 * fixLengthLeft("word", "AB", 8) = "wordABAB" fixLengthLeft("word", "AB",
	 * 3) = "wor"
	 */
	public static String fixLengthLeft(String s, String c, int n) {
		String str = s == null ? "" : s;

		int chinesCount = getChinesCount(s);

		if (chinesCount > 0) {
			n -= chinesCount;
		}

		if (str.length() < n) {
			str = patchStringRight(str, c, n);
		} else {
			str = cutStringRight(str, n);
		}
		return str;
	}

	/**
	 * �ж�s���ж��ٸ������ַ� Example: 001����Ƽ'ddsf! return 3
	 */
	public static int getChinesCount(String s) {
		if (isEmpty(s) == true)
			return 0;

		int chinesCount = 0;
		char[] sArray = s.toCharArray();
		for (int i = 0; i < sArray.length; i++) {
			if (charIsChinese(sArray[i]) == true) {
				chinesCount++;
			}
		}
		return chinesCount;
	}

	/**
	 * ���ַ�����������ַ�?����n������ StringHandle.fixLengthRight("ABCDEFG",'Y',10) =>
	 * YYYABCDEFG fix the string' length(from right)
	 * 
	 * @param s
	 *            String
	 * @param c
	 *            char
	 * @param n
	 *            int
	 * @return String
	 */
	public static String fixLengthRight(String s, char c, int n) {
		return fixLengthRight(s, String.valueOf(c), n);
	}

	/**
	 * ���ַ�����������ַ�?����n������ fix the string' length(from right)
	 * fixLengthRight("word", "AB", 8) = "ABABword" fixLengthRight("word", "AB",
	 * 3) = "ord"
	 */
	public static String fixLengthRight(String s, String c, int n) {
		String str = s == null ? "" : s;
		if (str.length() < n) {
			str = patchStringLeft(str, c, n);
		} else {
			str = cutStringLeft(str, n);
		}
		return str;
	}

	/*
	 * (9)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * get the left part of string seperated by delimeter
	 */
	public static String getLeftPart(String s, String delim) {
		return getLeftPart(s, delim, 0);
	}

	/**
	 * get the left part of string seperated by delimeter
	 */
	public static String getLeftPart(String s, String delim, int start) {
		return getLeftPart(s, delim, start, true);
	}

	/**
	 * get the left part of string seperated by delimeter
	 */
	public static String getLeftPart(String s, String delim, boolean ignoreCase) {
		return getLeftPart(s, delim, 0, ignoreCase);
	}

	/**
	 * get the left part of string seperated by delimeter
	 */
	public static String getLeftPart(String s, String delim, int start,
			boolean ignoreCase) {
		if (s == null || delim == null || delim.equals("")) {
			return s;
		}
		int place = 0;
		String s1 = null, d1 = null;
		s1 = ignoreCase ? s.toLowerCase() : s;
		d1 = ignoreCase ? delim.toLowerCase() : delim;
		place = s1.indexOf(d1, start);
		if (place >= 0) {
			s1 = s.substring(0, place);
		} else {
			s1 = s;
		}
		return s1;
	}

	/**
	 * get the left part of string seperated by delimeter
	 */
	public static String getLastLeftPart(String s, String delim) {
		return getLastLeftPart(s, delim, 0);
	}

	/**
	 * get the left part of string seperated by delimeter
	 */
	public static String getLastLeftPart(String s, String delim, int start) {
		return getLastLeftPart(s, delim, start, true);
	}

	/**
	 * get the left part of string seperated by delimeter
	 */
	public static String getLastLeftPart(String s, String delim, int start,
			boolean ignoreCase) {
		if (s == null || delim == null || delim.equals("")) {
			return s;
		}
		int place = 0;
		String s1 = null, d1 = null;
		s1 = ignoreCase ? s.toLowerCase() : s;
		d1 = ignoreCase ? delim.toLowerCase() : delim;
		place = s1.lastIndexOf(d1, s1.length());
		if (place >= 0) {
			s1 = s.substring(0, place);
		} else {
			s1 = s;
		}
		return s1;
	}

	/**
	 * get the right part of string seperated by delimeter
	 */
	public static String getRightPart(String s, String delim) {
		return getRightPart(s, delim, 0);
	}

	/**
	 * get the right part of string seperated by delimeter
	 */
	public static String getRightPart(String s, String delim, int start) {
		return getRightPart(s, delim, start, true);
	}

	/**
	 * get the right part of string seperated by delimeter
	 */
	public static String getRightPart(String s, String delim, boolean ignoreCase) {
		return getRightPart(s, delim, 0, ignoreCase);
	}

	/**
	 * get the right part of string seperated by delimeter
	 */
	public static String getRightPart(String s, String delim, int start,
			boolean ignoreCase) {
		if (s == null || delim == null || delim.equals("")) {
			return s;
		}
		int place = 0;
		String s1 = null, d1 = null;
		s1 = ignoreCase ? s.toLowerCase() : s;
		d1 = ignoreCase ? delim.toLowerCase() : delim;
		place = s1.indexOf(d1, start);
		if (place >= 0) {
			place = place <= s1.length() ? place + d1.length() : s1.length();
			s1 = s.substring(place);
		} else {
			s1 = "";
		}
		return s1;
	}

	/**
	 * Gets the middle part between the two delimeter in the string
	 */
	public static String getMiddlePart(String s, String delim1, String delim2) {
		return getMiddlePart(s, delim1, delim2, true);
	}

	/**
	 * Gets the middle part between the two delimeter in the string
	 */
	public static String getMiddlePart(String s, String delim1, String delim2,
			boolean ignoreCase) {
		return getLeftPart(getRightPart(s, delim1, ignoreCase), delim2,
				ignoreCase);
	}

	/*
	 * (11)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * patch string with c to specified length(from left)
	 */
	public static String patchStringLeft(String s, char c, int n) {
		return patchStringLeft(s, String.valueOf(c), n);
	}

	/**
	 * patch string with c to specified length(from left)
	 * patchStringRight("word", "AB", 8) --> "ABABword"
	 */
	public static String patchStringLeft(String s, String c, int n) {
		String s1 = s == null ? "" : s, str = "";
		if (c == null || c.equals("")) {
			return patchStringLeft(s, ' ', n);
		}
		while ((s1 + str).length() + c.length() <= n) {
			str += c;
		}
		if ((s1 + str).length() < n) {
			str += c.substring(0, n - (s1 + str).length());
		}
		str = str + s1;
		return str;
	}

	/**
	 * patch string with c to specified length(from right)
	 */
	public static String patchStringRight(String s, char c, int n) {
		return patchStringRight(s, String.valueOf(c), n);
	}

	/**
	 * patch string with c to specified length(from right)
	 * patchStringRight("word", "12", 8) --> "word1212"
	 */
	public static String patchStringRight(String s, String c, int n) {
		String str = s == null ? "" : s;
		if (c == null || c.equals("")) {
			return patchStringRight(s, ' ', n);
		}
		while (str.length() + c.length() <= n) {
			str += c;
		}
		if (str.length() < n) {
			str += c.substring(0, n - str.length());
		}
		return str;
	}

	/*
	 * (12)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ���ַ�replaceSource��replaceFrom�ַ��滻��replaceTo�ַ�(ע����ִ�С�?
	 * StringHandle.replace("AAAAAAAAAAABBBBBBABBBBAAAAA",'A','c')
	 * =>AAAAAAAAAAABBBBBBABBBBAAAAA
	 * 
	 * @param replaceSource
	 *            String
	 * @param replaceFrom
	 *            char
	 * @param replaceTo
	 *            char
	 * @return String
	 */
	public static String replace(String replaceSource, char replaceFrom,
			char replaceTo) {
		String result = "";
		if (replaceSource == null || replaceSource.equals("")) {
			return new String(replaceSource);
		}

		int i = 0;

		while (i < replaceSource.length()) {
			if (replaceSource.charAt(i) == replaceFrom) {
				result = result + replaceTo;
			} else {
				result = result + replaceSource.substring(i, i + 1);
			}
			i++;
		}
		return result;
	} // method replace

	/**
	 * ���ַ�replaceSource��replaceFrom�ַ��滻��replaceTo�ַ�(ע����ִ�С�?
	 * 
	 * @param replaceSource
	 *            String
	 * @param replaceFrom
	 *            char
	 * @param replaceTo
	 *            String
	 * @return String
	 */
	public static String replace(String replaceSource, char replaceFrom,
			String replaceTo) {
		String result = "";
		if (replaceSource == null || replaceSource.equals("")) {
			return new String(replaceSource);
		}

		int i = 0;

		while (i < replaceSource.length()) {
			if (replaceSource.charAt(i) == replaceFrom) {
				result = result + replaceTo;
			} else {
				result = result + replaceSource.substring(i, i + 1);
			}
			i++;
		}
		return result;
	}

	/**
	 * ���ַ�replaceSource��replaceFrom�ַ��滻��replaceTo�ַ�(ע����ִ�С�?
	 * 
	 * @param replaceSource
	 *            String
	 * @param replaceFrom
	 *            String
	 * @param replaceTo
	 *            String
	 * @return String
	 */
	public static String replace(String replaceSource, String replaceFrom,
			String replaceTo) {
		String result = "";
		if (replaceSource == null || replaceSource.equals("")) {
			return new String(replaceSource);
		}

		int i = 0;

		while (i < replaceSource.length()) {
			if ((i <= replaceSource.length() - replaceFrom.length())
					&& replaceSource.substring(i, i + replaceFrom.length())
							.equals(replaceFrom)) {
				result = result + replaceTo;
				i = i + replaceFrom.length();
			} else {
				result = result + replaceSource.substring(i, i + 1);
				i++;
			}
		}
		return result;
	}

	/**
	 * ���ַ�replaceSource��replaceFrom�ַ��滻��replaceTo�ַ�
	 * 
	 * @param replaceSource
	 *            String
	 * @param replaceFrom
	 *            String
	 * @param replaceTo
	 *            String
	 * @param bMatchCase
	 *            boolean
	 * @return String
	 */
	public static String replace(String replaceSource, String replaceFrom,
			String replaceTo, boolean bMatchCase) {
		String result = "";
		if (replaceSource == null || replaceSource.equals("")) {
			return new String(replaceSource);
		}

		int i = 0;

		while (i < replaceSource.length()) {
			if (!bMatchCase) {
				if ((i <= replaceSource.length() - replaceFrom.length())
						&& replaceSource.substring(i, i + replaceFrom.length())
								.toLowerCase()
								.equals(replaceFrom.toLowerCase())) {
					result = result + replaceTo;
					i = i + replaceFrom.length();
				} else {
					result = result + replaceSource.substring(i, i + 1);
					i++;
				}
			} else {
				if ((i <= replaceSource.length() - replaceFrom.length())
						&& (replaceSource
								.substring(i, i + replaceFrom.length()))
								.equals(replaceFrom)) {
					result = result + replaceTo;
					i = i + replaceFrom.length();
				} else {
					result = result + replaceSource.substring(i, i + 1);
					i++;
				}
			}
		}
		return result;
	}

	public static String replace(String source, String from, String to,
			int beginIndex) {
		String left = source.substring(0, beginIndex);
		String right = source.substring(beginIndex);

		right = right.replaceFirst(from, to);

		return left + right;
	}

	/*
	 * (13)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ���S1����S2��ͷ��,��ȥ��S2���� Minuses two strings minusString("wordaWordb",
	 * "worda") --> "Wordb" StringHandle.minusString("ABCDAAAAEABCD","ABCD") =>
	 * AAAAEABCD
	 */
	public static String minusString(String s1, String s2) {
		return minusString(s1, s2, true);
	}

	/**
	 * ���S1����S2��ͷ��,��ȥ��S2���� Minuses two strings minusString("wordaWordb",
	 * "worda") --> "Wordb" StringHandle.minusString("ABCDAAAAEABCD","ABCD") =>
	 * AAAAEABCD
	 */
	public static String minusString(String s1, String s2, boolean ignoreCase) {
		if (s1 == null || s2 == null) {
			return s1;
		}
		String sa, sb;
		sa = ignoreCase ? s1.toLowerCase() : s1;
		sb = ignoreCase ? s2.toLowerCase() : s2;
		if (sa.startsWith(sb)) {
			sa = s1.substring(sb.length());
		} else {
			sa = s1;
		}
		return sa;
	}

	/**
	 * ���S1����S2��β��,��ȥ��β����S2���� Minuses two strings minusString("wordaWordb",
	 * "worda") --> "Wordb" StringHandle.minusLastString("ABCDAAAAEABCD","ABCD")
	 * => "ABCDAAAAE"
	 */
	public static String minusLastString(String s1, String s2) {
		return minusLastString(s1, s2, true);
	}

	/**
	 * ���S1����S2��β��,��ȥ��β����S2���� Minuses two strings minusString("wordaWordb",
	 * "worda") --> "Wordb"
	 */
	public static String minusLastString(String s1, String s2,
			boolean ignoreCase) {
		if (s1 == null || s2 == null) {
			return s1;
		}
		String sa, sb;
		sa = ignoreCase ? s1.toLowerCase() : s1;
		sb = ignoreCase ? s2.toLowerCase() : s2;
		if (sa.endsWith(sb)) {
			sa = s1.substring(0, sa.length() - sb.length());
		} else {
			sa = s1;
		}
		return sa;
	}

	/*
	 * (14)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ȥ���ַ������еĿո񣬻س�,����, ' ' \r \n \t
	 * 
	 * @return a string, spaces & \r & \n trimed for example,
	 *         "byWhiteShenbyWhiteShen" will be returned , if the string likes
	 *         "   by White Shen\r\n   by White Shen  \r \n \t"
	 */
	public static String trimAll(String str) {
		String s = "";
		char c;

		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c != ' ' && c != '\r' && c != '\n' && c != '\t')
				s += c;
		}
		return s;
	}

	/**
	 * ��' ',\t,\r,\n�û���' ',�����������ַ���ϵ��һ��,��ô��һ����ַ�ϲ�Ϊһ���ո�. ���ȥ��ǰ��Ŀո�.
	 * ���\r\n' '�ϲ�Ϊһ��' ',����ո���һ��Ҳ�ϲ�Ϊһ���ո�?
	 * Example:StringHandle.trimRedundantSpace(
	 * "   by White Shen\r\n   by White Shen  \r \n \tAAAA   "
	 * )=>"by White Shen by White Shen AAAA" trim all the redundant space,
	 * includes '\n' & '\r' & '\t'
	 */
	public static String trimRedundantSpace(String s) {
		boolean preIsSpace = false;
		char c;
		String rs = "";

		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
				if (!preIsSpace) {
					rs += ' ';
				}
				preIsSpace = true;
			} else {
				rs += c;
				preIsSpace = false;
			}
		}
		return rs.trim();
	}

	/*
	 * (15)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ȥ���ַ�β��'\\'
	 * Example:StringHandle.stringWithoutLastSlash("AAAAAAAAAAABBBBBBBBBB\\") =>
	 * AAAAAAAAAAABBBBBBBBBB Removes the last return from slash
	 */
	public static String stringWithoutLastSlash(String str) {
		String s = null;

		s = str;
		if (s == null) {
			return null;
		}
		if (s.endsWith("\\")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	/**
	 * ȥ���ַ�β��'\n','\r'
	 * Example:StringHandle.stringWithoutLastSlash("AAAAAAAAAAABBBBBBBBBB\n\r")
	 * => AAAAAAAAAAABBBBBBBBBB remove the last return from string
	 */
	public static String stringWithoutLastReturn(String str) {
		String s = null;
		s = str;
		if (s == null) {
			return null;
		}
		if (s.endsWith("\n")) {
			s = s.substring(0, s.length() - 1);
		}
		if (s.endsWith("\r")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	/*
	 * (16)
	 * ----------------------------------------------------------------------
	 * -----------------
	 */
	/**
	 * ���n��ո�?
	 * 
	 * @param n
	 *            int
	 * @return String
	 */
	public static String nSpace(int n) {
		return nChar(' ', n);
	}

	/**
	 * ���n���ַ�
	 * 
	 * @param c
	 *            char
	 * @param n
	 *            int
	 * @return String
	 */
	public static String nChar(char c, int n) {
		return nChar(String.valueOf(c), n);
	}

	/**
	 * �����n���ַ����ӳɵ��ַ�
	 * 
	 * @param s
	 *            String
	 * @param n
	 *            int
	 * @return String
	 */
	public static String nChar(String s, int n) {
		return nChar(s, (long) n);
	}

	/**
	 * �����n���ַ����ӳɵ��ַ�
	 * 
	 * @param s
	 *            String
	 * @param n
	 *            long
	 * @return String
	 */
	public static String nChar(String s, long n) {
		StringBuffer sb = new StringBuffer();
		for (long i = 0; i <= n - 1; i++) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static String encrypt(String source) {
		char[] sArray = source.toCharArray();
		for (int i = 0; i < sArray.length; i++) {
			sArray[i] = (char) (sArray[i] ^ 't');
		}

		return new String(sArray);
	}

	public static String decrypt(String source) {
		char[] sArray = source.toCharArray();
		for (int i = 0; i < sArray.length; i++) {
			sArray[i] = (char) (sArray[i] ^ 't');
		}

		return new String(sArray);
	}

	/**
	 * ���ַ���ܳ�һ��Des��
	 */
	public static String desEncrypt(String source, String key) {
		byte[] bytK1 = getDesKeyByStr(key.substring(0, 16));
		byte[] bytK2 = getDesKeyByStr(key.substring(16, 32));
		byte[] bytK3 = getDesKeyByStr(key.substring(32, 48));

		// Des��μ���?
		try {
			byte[] bytOut = encryptByDES(encryptByDES(encryptByDES(source
					.getBytes(), bytK1), bytK2), bytK3);

			return new String(bytOut);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
     * 
     */
	public static String desDecrypt(String source, String key) {
		return null;
	}

	/**
	 * ��DES��������������ֽ�?bytKey��Ϊ8�ֽڳ����Ǽ��ܵ�����
	 */
	private static byte[] encryptByDES(byte[] bytP, byte[] bytKey)
			throws Exception {
		DESKeySpec desKS = new DESKeySpec(bytKey);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		Cipher cip = Cipher.getInstance("DES");
		cip.init(Cipher.ENCRYPT_MODE, sk);
		return cip.doFinal(bytP);
	}

	/**
	 * ����������ַ���ʽ�������ֽ�������ʽ��?�������ַ�AD67EA2F3BE6E5AD �����ֽ����飺{
	 * 173,103,234,47,59,230,229,173 }
	 */
	private static byte[] getDesKeyByStr(String str) {
		byte[] bRet = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			Integer itg = new Integer(16 * getDesChrInt(str.charAt(2 * i))
					+ getDesChrInt(str.charAt(2 * i + 1)));
			bRet[i] = itg.byteValue();
		}
		return bRet;
	}

	/**
	 * ����һ��16�����ַ��?0����ֵ ���룺0-F
	 */
	private static int getDesChrInt(char chr) {
		int iRet = 0;
		if (chr == "0".charAt(0))
			iRet = 0;
		if (chr == "1".charAt(0))
			iRet = 1;
		if (chr == "2".charAt(0))
			iRet = 2;
		if (chr == "3".charAt(0))
			iRet = 3;
		if (chr == "4".charAt(0))
			iRet = 4;
		if (chr == "5".charAt(0))
			iRet = 5;
		if (chr == "6".charAt(0))
			iRet = 6;
		if (chr == "7".charAt(0))
			iRet = 7;
		if (chr == "8".charAt(0))
			iRet = 8;
		if (chr == "9".charAt(0))
			iRet = 9;
		if (chr == "A".charAt(0))
			iRet = 10;
		if (chr == "B".charAt(0))
			iRet = 11;
		if (chr == "C".charAt(0))
			iRet = 12;
		if (chr == "D".charAt(0))
			iRet = 13;
		if (chr == "E".charAt(0))
			iRet = 14;
		if (chr == "F".charAt(0))
			iRet = 15;
		return iRet;
	}

	/**
	 * ���ַ���ܳ�һ��md5��
	 */
	public static String Md5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			plainText += "-11";
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	/**
	 * ���Ϊ���ַ�ת�?0
	 * @param ���׷�
	 * @return
	 */
	
	public static String NullToZero(String str){
		if (str==null||"".equals(str)) {
			return "0";
		}
		return str;
	}
	public static String getParse(String format,Object object){
		DecimalFormat df = new DecimalFormat(format);
		return df.format(object);	 
	}
}
