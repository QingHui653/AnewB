package test.core.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class IteratorTest {

	@Test
	public void operationTest() {

		List<String> list1= new ArrayList<String>();
		list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List<String> list2 = new ArrayList<String>();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        // 并集  有重复
        // list1.addAll(list2);
        // 交集
        // list1.retainAll(list2);
        // 差集
        // list1.removeAll(list2);
        // 无 重复并集
        list2.removeAll(list1);
        list1.addAll(list2);
        //for
		for (Iterator<String> iter = list1.iterator(); iter.hasNext();) {
			String str = (String) iter.next();
			System.out.println(str);
		}

        // while
        Iterator<String> it = list1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

	}
}
