package test.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;


public class HashMapTest {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		
		for (Map.Entry<String,String> entry:map.entrySet()) {
			System.out.println(entry.getKey()+"="+entry.getValue());
		}

		try {
		File f =new File("G:\\1.txt");
		InputStream i;
		i = new FileInputStream(f);
		
		StringWriter writer = new StringWriter();
		
		IOUtils.copy(i, writer, "UTF-8");
		
		String theString = writer.toString();
		System.out.println("  one "+theString);
		
		String theString2 = IOUtils.toString(i, "UTF-8");
		System.out.println("  two "+theString2);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	}

}
