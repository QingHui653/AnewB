package newb.c.backend.service;

import java.util.List;


import newb.c.backend.model.UserTrin;
import newb.c.backend.model.basemodel.User;

public interface UserService extends IService<User> {  
	
    User getUserById(int id);  
    
    List<User> getUsers();
    
    int insertAll(List<User> userList);
    
    List<User> selectAllForUpdate();
    
    String selectPW(int oid);
    
    UserTrin selectUserCacheByUser(int oid);
    
    UserTrin selectUserCacheByDao(int oid);
    
    String testMock(String name);
}
