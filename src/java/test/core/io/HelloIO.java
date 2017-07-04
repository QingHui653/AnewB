package test.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class HelloIO {
	
	public static void main(String[] args) {
		System.out.println("Hello IO!");
		
		byte[] buf = new byte[10];
        System.out.println("输入姓名   字节? ");
        int n = 0;
        try {
            n = System.in.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("hello, ");
        //查看buf内部，能看出内部为字节
        //[-26, -75, -73, -25, -70, -77, 13, 10,
        //前三个字节为一组  ，UTF-8解码 与 unicode码对应
        System.out.write(buf, 0, n);
        /**
         * 因解码工作的麻烦，java引入字节流转为字符流的适配器
         * 适配器模式
         * 传入的为字节流，通过InputStreamReader将字节转为字符
         */
        
        char[] cbuf = new char[10];
        System.out.println("输入姓名  字符? ");
        n = 0;
        Reader r = new InputStreamReader(System.in);
        try {
            n = r.read(cbuf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //查看cbuf，能看见内部是char，不是字节
        System.out.println("hello," + cbuf[0]);
        
        /**
         * bufferedReader 看构造器new了一个char数组，当读取时
         * 直接从 char数组中读取，即buff
         * 但bufferedReader还是读取的是reader类
         * 因此这又叫装饰者模式，即为对象增加额外能力
         */
        
        System.out.println("输入姓名 buffreader? ");
        n = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
			System.out.println("hello,"+br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
