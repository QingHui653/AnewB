package test.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;

public class inputStream_to_String {
	
	public static void main(String[] args) throws URISyntaxException {
		try {
			
		//获取文件流
//		File f =new File("G:\\1.txt");
//		URL u =	inputStream_to_String.class.getResource("1.txt");
//		File f =new File(u.toURI()); 
		InputStream i = inputStream_to_String.class.getResourceAsStream("1.txt");
		//apache io 输入流转String 格式
		StringWriter writer = new StringWriter();
		IOUtils.copy(i, writer, "UTF-8");
		
		String theString = writer.toString();
		System.out.println("  one "+theString);
		//第一种的简写型,两种不可同时测试
		String theString2 =IOUtils.toString(i,"UTF-8");
		System.out.println("  two "+theString2);
		i.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
