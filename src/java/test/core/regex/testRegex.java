package test.core.regex;

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
		
		String url =  "http://www.80s.tw/movie/list/-2016----";
        Pattern pattern1 = Pattern.compile("http://www.80s.tw/movie/list/-2016----(p\\d*)?");
        Matcher matcher1 = pattern1.matcher(url);
        
        if(matcher1.find()){
        	System.out.println("Found value: " + matcher1.group(0) );
        }else {
        	System.out.println("无");
		}
        
        Pattern pattern2 = Pattern.compile("/movie/\\d+");
        Matcher matcher2 = pattern2.matcher(url);
        
        if(matcher2.find()){
        	System.out.println("Found value: " + matcher2.group(0) );
        }else {
        	System.out.println("无");
		}
	}

}
