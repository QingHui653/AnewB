package test.core.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import newb.c.backend.sql.model.basemodel.User;
public class ObjectSort {
	
	public static void main(String[] args) {
		User user1=new User(5, "1", "2");
		User user2=new User(9, "2", "2");
		User user3=new User(3, "3", "3");
		
		User[]  UserList ={user1,user3,user2};
		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
		Arrays.sort(UserList);
		Arrays.sort(UserList,new ComparatorDemo());
		System.out.println("---"+Arrays.toString(UserList));
		//通常使用这种方式，因为排序大部分是不同的
		Comparator<Object> c = new Comparator<Object>(){  
            @Override  
            public int compare(Object arg0, Object arg1) { //这里实现按照用户年龄大小来排序  
  
            	User temp1 = (User) arg0;  
            	User temp2 = (User) arg1;  
              
            if(temp1.getOid()>temp2.getOid())return 1;  
            else if(temp1.getOid()<temp2.getOid())return -1;  
            else if(temp1.getOid()==temp2.getOid())return 0;  
            return 0;  
            }  
              
        }; 
		Collections.sort(userList,c);
		System.out.println(userList.toString());
		
		//使用外部类的方式
		ComparatorDemo comparatorDemo =new ComparatorDemo();
		Collections.sort(userList,comparatorDemo);
		System.out.println(userList.toString());
		
//		Collections.sort(userList,(a,b) -> a.getOid()-b.getOid());
//		System.out.println(userList.toString());
	}
}
