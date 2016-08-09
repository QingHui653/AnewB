package newb.c.service;

import java.util.List;

import newb.c.model.User;


public interface UserService {  
	  
    User getUserById(int id);  
      
      
    int insert(User userInfo); 
    
    List<User> getUsers();
}
