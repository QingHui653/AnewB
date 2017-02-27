package test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class FileDirTest {
	
	@Test
	public void fileDir() throws IOException {
		File f1 =new File("D:\\233\\1.txt");
		FileInputStream fio1= new FileInputStream(f1);
		String theString1 =IOUtils.toString(fio1,"UTF-8");
		System.out.println(" io1 "+theString1);
		fio1.close();
		
		File f2 =new File("src/test/io/1.txt");
		FileInputStream fio2= new FileInputStream(f2);
		String theString2 =IOUtils.toString(fio2,"UTF-8");
		System.out.println(" io2 "+theString2);
		fio2.close();
		
		FileInputStream fio3= new FileInputStream("src/test/io/1.txt");
		String theString3 =IOUtils.toString(fio3,"UTF-8");
		System.out.println(" io3 "+theString3);
		fio3.close();
		
		FileInputStream fio4= new FileInputStream(new File("src/test/io/1.txt"));
		String theString4 =IOUtils.toString(fio4,"UTF-8");
		System.out.println(" io4 "+theString4);
		fio4.close();
	}
}
