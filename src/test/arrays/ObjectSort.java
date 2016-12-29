package test.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import newb.c.backend.model.basemodel.User;  
public class ObjectSort {
	
	public static void main(String[] args) {
		User user1=new User(5, "1", "2");
		User user2=new User(9, "2", "2");
		User user3=new User(3, "3", "3");
		
		User[]  UserList ={user1,user3,user2};
		
		Arrays.sort(UserList);
		System.out.println("---"+Arrays.toString(UserList));
	}
}
