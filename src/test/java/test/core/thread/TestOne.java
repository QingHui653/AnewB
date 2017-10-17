package test.core.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 单线程 写入文件
 * @author woshizbh
 *
 */
public class TestOne {
 
    public static void main(String[] args) {
        long millis1 = System.currentTimeMillis();
        String fileName = "C:\\Users\\woshizbh\\Desktop\\测试0.txt"; // 文件名
        try {
        	File f=new File(fileName);
        	if(f.exists()){f.createNewFile();}
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
            
            for(int i=0;i<=100000;i++){
                String temp = "单线程: " + i + "----------------------------------我是一条华丽的小尾巴";
                writer.write(temp);
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {        
            e.printStackTrace();
        } 
        
        long millis2 = System.currentTimeMillis();
        System.out.println(millis1);
        System.out.println(millis2);
        System.out.println(millis2 - millis1);  //大约162-168ms
    }
 
}
