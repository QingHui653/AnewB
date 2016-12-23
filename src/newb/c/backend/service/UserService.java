package newb.c.backend.service;

import java.util.List;


import newb.c.model.User;
import newb.c.model.UserTrin;

public interface UserService extends IService<User> {  
	
    User getUserById(int id);  
    
    List<User> getUsers();
    
    int insertAll(List<User> userList);
    
    List<User> selectAllForUpdate();
    
    String selectPW(int oid);
    
    UserTrin selectUserCacheByUser(int oid);
    
    UserTrin selectUserCacheByDao(int oid);
}
