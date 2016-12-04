package test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class inputStream_to_String {
	
	public static void main(String[] args) throws URISyntaxException {
		try {
//		URL u =	inputStream_to_String.class.getResource("1.txt");
//		File f =new File(u.toURI()); 
		InputStream i = inputStream_to_String.class.getResourceAsStream("1.txt");
		//apache io 输入流转String 格式
		/*StringWriter writer = new StringWriter();
		IOUtils.copy(i, writer, "UTF-8");
		String theString = writer.toString();
		System.out.println("  one "+theString);*/
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
	
	@Test
	public void io1() throws IOException {
		File f =new File("G:\\1.txt");
		FileInputStream fio= new FileInputStream(f);
		String theString3 =IOUtils.toString(fio,"UTF-8");
		System.out.println(" io1 "+theString3);
		fio.close();
	}
	
	@Test
	public void io2() throws IOException {
		File f =new File("G:\\1.txt");
		FileInputStream fio= new FileInputStream(f);
		String theString3 =IOUtils.toString(fio,"UTF-8");
		System.out.println(" io2 "+theString3);
		fio.close();
	}
	
	@Test
	public void io3() throws IOException {
		String a = "aaa测试中文";
		File f =new File("G:\\2.txt");
		if(!f.exists()) f.createNewFile();
		OutputStreamWriter  out =new OutputStreamWriter (new FileOutputStream(f),"UTF-8" );
		out.write(a);
		out.flush();
		out.close();
		System.out.println(" io3 "+"文件写入成功");
	}
}
