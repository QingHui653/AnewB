package newb.c.backend.sql.service;

import java.util.List;


import newb.c.backend.sql.model.UserTrin;
import newb.c.backend.sql.model.basemodel.User;

public interface UserService extends IService<User> {
	
    User getUserById(int id);  
    
    List<User> getUsers();
    
    int insertAll(List<User> userList);
    
    int threadInsertAll(int page);
    
    List<User> selectAllForUpdate();
    
    String selectPW(int oid);
    
    UserTrin selectUserCacheByUser(int oid);
    
    UserTrin selectUserCacheByDao(int oid);
    
    String testMock(String name);
}
