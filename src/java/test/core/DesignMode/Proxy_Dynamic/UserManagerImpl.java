package test.core.DesignMode.Proxy_Dynamic;

public class UserManagerImpl implements UserManager {  
	  
    /***** 
     * 添加用户 
     */  
    public void addUser(String userId, String userName) {  
            System.out.println("正在添加用户,用户为："+userId+userName+"……");  
    }  
    /***** 
     * 删除用户 
     */  
    public void delUser(String userId) {  
        System.out.println("delUser,userId="+userId);  
    }  
    /*** 
     * 查找用户 
     */  
    public String findUser(String userId) {  
        System.out.println("findUser,userId="+userId);  
        return userId;  
    }  
  
    public void modifyUser(String userId, String userName) {  
        System.out.println("modifyUser,userId="+userId);  
    }  
}  
