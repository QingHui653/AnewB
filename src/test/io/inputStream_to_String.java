package test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

public class inputStream_to_String {
	
	public static void main(String[] args) {
		try {
		File f =new File("G:\\1.txt");
		InputStream i = new FileInputStream(f);
		//apache io 输入流转String 格式
		StringWriter writer = new StringWriter();
		IOUtils.copy(i, writer, "UTF-8");
		
		String theString = writer.toString();
		System.out.println("  one "+theString);
		//第一种的简写型，不知道为什么不对。
		String theString2 =IOUtils.toString(i,"UTF-8");
		System.out.println("  two "+theString2);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
