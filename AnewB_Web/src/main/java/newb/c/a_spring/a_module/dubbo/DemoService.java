package newb.c.a_spring.a_module.dubbo;

import java.util.List;

import newb.c.a_spring.backend.sql.model.basemodel.User;

public interface DemoService {
	
	String sayHello(String name);

	public List<User> getUsers();
}
