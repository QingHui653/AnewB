package test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testRegex {

	public static void main(String[] args) {
		String s="This order was placed for QT3000! OK?";
		
		Pattern p =Pattern.compile("(.*)(\\d+)(.*)");
		Matcher m =p.matcher(s);
		
		if (m.find()) {
			System.out.println("Found value: " + m.group(0) );
		}else {
			System.out.println("NO MATCH");
		}
	}

}
