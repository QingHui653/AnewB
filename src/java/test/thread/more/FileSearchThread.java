package test.thread.more;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class FileSearchThread {
	public static void main(String[] args) throws IOException {
		File f= new File("C:\\Users\\woshizbh\\Desktop\\java");
		new FileSearchThread().search(f, "java");
	}

	public void search(File file, String search) throws IOException {
		if (!file.isFile()) {
			// 列出该目录下的所有文件和目录
			File files[] = file.listFiles();
			// 遍历files[]数组
			for (File f : files) {
				// 递归
				search(f, "java");
			}
		} else {
			FileInputStream fio = new FileInputStream(file);
			Thread t= new Thread(new Task(fio));
			t.start();
		}
	}
}

class Task implements Runnable {
	
	private FileInputStream fio;

	public Task(FileInputStream fio) {
		super();
		this.fio = fio;
	}

	@Override
	public void run() {
		try {
			String javaContent = IOUtils.toString(fio, "UTF-8");
			System.out.println(javaContent);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("-------"+Thread.currentThread().toString()+"--已读完--------------");
		}
	}
	
}

