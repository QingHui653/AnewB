package newb.c.dubbo;

import java.util.List;

import newb.c.model.User;

public interface DemoService3 {
	
	String sayHello(String name);

	public List<User> getUsers();
}
