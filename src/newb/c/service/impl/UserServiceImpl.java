package newb.c.service.impl;

import java.util.List;

import newb.c.dao.UserMapper;
import newb.c.model.User;
import newb.c.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper UserMapper;
	
	@Override
	public User getUserById(int id) {
//		UserMapper.selectByPrimaryKey(id);
		User user = UserMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public int insert(User userInfo) {
		int result = UserMapper.insert(userInfo);
		System.out.println("res"+result);
		return result;
		
	}

	@Override
	public List<User> getUsers() {
		return UserMapper.selectAll();
	}

}
