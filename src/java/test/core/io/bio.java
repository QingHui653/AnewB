package test.core.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Test;


public class bio {
	
	@Test
	public void path() {
		String path= bio.class.getResource("/").getPath();
		System.out.println("当前class文件的路径  "+path);
		String path2= getClass().getResource("/").getPath();
		System.out.println("路径  "+path2);
		//取得根目录路径  
	    String rootPath=getClass().getResource("/").getFile().toString();  
	    System.out.println("根目录路径    "+rootPath);
	    //当前目录路径  
	    String currentPath1=getClass().getResource(".").getFile().toString();  
	    System.out.println("当前目录路径    "+currentPath1);
	    String currentPath2=getClass().getResource("").getFile().toString();  
	    System.out.println("当前目录路径    "+currentPath2);
	    //当前目录的上级目录路径  
	    String parentPath=getClass().getResource("../").getFile().toString();
		System.out.println("当前目录的上级目录路径    "+parentPath);
		String relativelyPath=System.getProperty("user.dir");
		System.out.println("当前绝对路径    "+relativelyPath);
	}
	
	/*@Test*/
	public void io1() throws IOException {
	    String relativelyPath=System.getProperty("user.dir");
		File f =new File(relativelyPath+"\\src\\test\\io\\1.txt");
		FileInputStream fio= new FileInputStream(f);
		String theString3 =IOUtils.toString(fio,"UTF-8");
		System.out.println(" io1 "+theString3);
		fio.close();
	}
	
	/*@Test*/
	public void io3() throws IOException {
		String a = "aaa测试中文";
		String relativelyPath=System.getProperty("user.dir");
		File f =new File(relativelyPath+"\\src\\test\\io\\2.txt");
		if(f.exists()){f.createNewFile();}
		OutputStreamWriter  out =new OutputStreamWriter (new FileOutputStream(f),"UTF-8" );
		out.write(a);
		out.flush();
		out.close();
		System.out.println(" io3 "+"文件写入成功");
	}
	
	/*@Test*/
	public void io3Buff() throws IOException {
		String a = "aaa测试中文buff";
		String relativelyPath=System.getProperty("user.dir");
		File f =new File(relativelyPath+"\\src\\test\\io\\2.txt");
		if(f.exists()){f.createNewFile();}
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
		BufferedWriter bw= new BufferedWriter(osw);
		BufferedWriter bos= new BufferedWriter(bw);
		bos.write(a);
		bos.flush();
		bos.close();
		System.out.println(" io3Buff "+"文件写入成功");
	}
	
	/*@Test*/
	public void io4Buff() throws IOException {
		String relativelyPath=System.getProperty("user.dir");
		File f =new File(relativelyPath+"\\src\\test\\io\\1.jpg");
		File f2 =new File(relativelyPath+"\\src\\test\\io\\2.jpg");
		if(!f2.exists()){f2.createNewFile();}
		FileInputStream fio=null;
		BufferedInputStream bis=null;
		OutputStream ops=null;
		BufferedOutputStream bos=null;
		int count = 0;
		try {
			fio = new FileInputStream(f);
			bis=new BufferedInputStream(fio, 10000);
			ops=new FileOutputStream(f2);
			bos= new BufferedOutputStream(ops, 10000);
			//缓冲区，看情况创建大小
			byte[] b = new byte[10240];
			int len;  //每次写入1024
			// 循环将输入流中的内容读取到缓冲区当中  
			while ((len = bis.read(b)) > 0) {  
				// 输出缓冲区的内容到浏览器，实现文件下载  
				bos.write(b, 0, len);
				count++;
			} 
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			bis.close();
			fio.close();
			bos.close();
			ops.close();
		}	
		   System.out.println(" count "+count);
		   System.out.println(" io4Buff "+"图片写入成功");
	}
	
	public void io4() throws IOException {
		String relativelyPath=System.getProperty("user.dir");
		File f =new File(relativelyPath+"\\src\\test\\io\\1.jpg");
		File f2 =new File(relativelyPath+"\\src\\test\\io\\2.jpg");
		if(!f2.exists()){f2.createNewFile();}
		FileInputStream fio=null;
		OutputStream ops=null;
		try {
			fio = new FileInputStream(f);
			ops=new FileOutputStream(f2);
			//缓冲区，看情况创建大小
			byte[] b = new byte[1024];
			int len;  //每次写入1024
			// 循环将输入流中的内容读取到缓冲区当中  
			while ((len = fio.read(b)) > 0) {  
				// 输出缓冲区的内容到浏览器，实现文件下载  
				ops.write(b, 0, len);  
			} 
			ops.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fio!=null){
				fio.close();
			}
			if(ops!=null){
				ops.close();
			}
		}
		   System.out.println(" io4 "+"图片写入成功");
	}
}
