package test.core.collection.list;

import java.util.ArrayList;
import java.util.List;

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

}
