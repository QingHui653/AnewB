package newb.c.a_module.dubbo;

import java.util.List;

import newb.c.backend.sql.model.basemodel.User;
public interface RmiDemoService {
	
	String sayHello(String name);

	public List<User> getUsers();
}
