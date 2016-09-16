package newb.c.utilDb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
	private static Properties pt;
	
	public GetProperties(){
		InputStream is = getClass().getResourceAsStream("/pt.properties");
		pt = new Properties();
		try {
			pt.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key){
		return pt.getProperty(key);
	}
	
	
	
	public static void main(String[] args) {
		GetProperties t = new GetProperties();
		System.out.println(t.getProperty("HR_URL"));
		System.out.println(pt.get("HR_URL"));
	}
}
