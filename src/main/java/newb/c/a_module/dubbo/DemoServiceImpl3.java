package newb.c.a_module.dubbo;

import java.util.ArrayList;
import java.util.List;

import newb.c.backend.sql.model.basemodel.User;

public class DemoServiceImpl3 implements DemoService3 {

	@Override
	public String sayHello(String name) {
		return "Hello3 " + name;
	}

	@Override
	public List<User> getUsers() {
		  
        List<User> list = new ArrayList<>();  
        User u1 = new User();  
        u1.setOid(50);  
        u1.setUsername("111");
        u1.setUsername("111");
          
        User u2 = new User();  
        u2.setOid(50);  
        u2.setUsername("222");
        u2.setUsername("222");  
          
        User u3 = new User();  
        u3.setOid(50);  
        u3.setUsername("333");
        u3.setUsername("333");  
          
        list.add(u1);  
        list.add(u2);  
        list.add(u3);  
        return list; 
	}

}
