package test.aa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.io.output.FileWriterWithEncoding;

/**
 * 统计一篇英文中的单词,要求如下：
 * ①一共出现了多少个单词；②有多少个互不相同的单词；③给出每个单词出现的频率，并将这些单词按频率大小顺序输出到文件words.txt文件中。
 * */
public class WordStatistics {
	private BufferedReader bufferedReader = null;
	private BufferedWriter bufferedWriter = null;
	
	
	public static void main(String[] args) {
		WordStatistics wordStatistics = new WordStatistics();
		Map<String, Integer> word_map = wordStatistics.readFile();
		
//		for(Map.Entry<String, Integer> mapping : word_map.entrySet()){
//			System.out.println(mapping.getKey() + " : " + mapping.getValue());
//		}
		wordStatistics.sortAndWrite(word_map);
		
	}
	
	/**
	 * 从指定路径读取英文文章，并形成Map集合
	 * */
	public Map<String, Integer> readFile(){
		//读文件
		StringBuffer stringBuffer = new StringBuffer();
		try {
			/*bufferedReader = new BufferedReader(new FileReader(new File("F:\\text1.txt")));*/  //文件路径可自定义
			InputStreamReader isr = new InputStreamReader(new FileInputStream(new File("F:\\text1.txt")), "UTF-8");
			bufferedReader = new BufferedReader(isr);
			String line = "";
			while((line = bufferedReader.readLine()) != null)
				stringBuffer.append(line);
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//生成<单词,次数>键值对
		Pattern pattern = Pattern.compile("(\\.)? ");
		String[] words = pattern.split(stringBuffer.toString());
		Map<String, Integer> word_map = new HashMap<String, Integer>();
		for(String s : words){
			if(!word_map.containsKey(s)){
				word_map.put(s, 1);
			}
			else{
				int count = word_map.get(s);
				word_map.replace(s, count, count+1);
			}
		}
		return word_map;
	}
	
	/**
	 * 按单词的出现频率排序并输出到words.txt文件中
	 * */
	public void sortAndWrite(Map<String, Integer> word_map){
		//排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(word_map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue().compareTo(o2.getValue());
			}

		});
		
		//写入文件		
		try {
			bufferedWriter = new BufferedWriter(new FileWriterWithEncoding(new File("F:\\words.txt"),"UTF-8"));
			bufferedWriter.write("一共出现了 " + word_map.size() + " 个单词，每个单词和它出现的频率分别是：");
			bufferedWriter.flush();
			bufferedWriter.newLine();
			for(Map.Entry<String, Integer> mapping : list){
				bufferedWriter.write(mapping.getKey() + " : " + mapping.getValue());
				bufferedWriter.flush();
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();			
			System.out.println("Work Out");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
