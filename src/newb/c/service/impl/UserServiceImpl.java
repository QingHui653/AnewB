package newb.c.service.impl;

import java.util.List;

import newb.c.dao.UserMapper;
import newb.c.model.User;
import newb.c.model.UserTrin;
import newb.c.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Cacheable(value="default")
	public User getUserById(int id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	@Cacheable(value="guavaCache1hour")
	public List<User> getUsers() {
		return userMapper.selectAll();
	}

	@Override
	public int insertAll(List<User> userList) {
		return userMapper.insertAll(userList);
	}

	@Override
	@Cacheable(value="default")
	public List<User> selectAllForUpdate() {
		return userMapper.selectAllForUpdate();
	}

	@Override
	public String selectPW(int oid) {
		return userMapper.selectPW(oid);
	}

	@Override
	public UserTrin selectUserCacheByUser(int oid) {
		return userMapper.selectUserAndUserCacheByUser(oid);
	}

	@Override
	public UserTrin selectUserCacheByDao(int oid) {
		return userMapper.selectUserAndUserCacheByDao(oid);
	}
}
