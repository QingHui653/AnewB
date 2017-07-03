package test.core.exceptio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;

public class exception {

	public static void main(String[] args) {
		File f =new File("D:\\1.txt");
		try {
			InputStream i = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			//获取完整的堆栈异常
			String fullStackTrace = org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e);
			e.printStackTrace();
//			Thread.currentThread().getStackTrace();
			System.out.println("完整的堆栈异常" +fullStackTrace);
		}
		
	}

}
