package test.core.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

public class ListTest {

	public static void main(String[] args) {
		List<String> a= new ArrayList<String>();
		a.add("1");
		a.add("2");
		a.add("3");
		
		List<String> b= new ArrayList<String>();
		b.add("5");
		b.add("6");
		b.add("7");
		
		List<Object> l= new ArrayList<Object>();
		l.add(a);
		l.add("4");
		l.add(b);
		
		for (Object ListD : l) {
			System.out.println("List--"+ListD.toString());
		}
		
		for (int i = 0; i < l.size(); i++) {
			System.out.println(i+"--"+l.get(0));
			if (l.get(i).toString().contains("[")) {
				System.out.println("是数组");
			}
		}
	}
	
	
	@Test
	public void test() {
		List<String> list =new ArrayList<>();
		System.out.println(list.size());
		
		if(list==null){
			System.out.println("null");
		}
		
		if(list.size()>0){
			System.out.println(">0");
		}
	}

	/**
	 * 对Arraylist 进行 分页
	 * Apache的ListUtils
	 */
	@Test
	public void TestArrayListPartion() {

		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
		List<List<String>> partition = ListUtils.partition(list, 2);
		System.out.println(JSON.toJSONString(partition));
	}

	/**
	 * 对ArrayList 进入分页
	 * Guava
	 */
	public void TestArrayListPartionUseGuava() {
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
		List<List<String>> partition = Lists.partition(list, 2);
		System.out.println(JSON.toJSONString(partition));
	}

}
