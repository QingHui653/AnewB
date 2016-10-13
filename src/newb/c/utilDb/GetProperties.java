package newb.c.utilDb;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class GetProperties {
	
	private static Properties pt;
	
	public GetProperties(){
		//InputStream is = getClass().getResourceAsStream("/pt.properties");
		pt = new Properties();
		try {
			pt.load(new InputStreamReader(getClass().getResourceAsStream("/pt.properties"), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key){
		return pt.getProperty(key);
	}
	
	
	
	public static void main(String[] args) {
		GetProperties t = new GetProperties();
		System.out.println(t.getProperty("point"));
	}
}
