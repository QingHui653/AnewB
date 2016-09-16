package newb.c.service;

import java.util.List;


import newb.c.model.User;

public interface UserService extends IService<User> {  
	
    User getUserById(int id);  
    
    List<User> getUsers();
}
