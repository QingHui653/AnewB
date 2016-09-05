package newb.c.service.impl;

import java.util.List;

import newb.c.model.User;
import newb.c.service.UserService;

import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl extends BaseService<User> implements UserService {
	
	@Override
	public User getUserById(int id) {
		User user = mapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public List<User> getUsers() {
		return mapper.selectAll();
	}

}
