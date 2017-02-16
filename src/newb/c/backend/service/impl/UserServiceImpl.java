package newb.c.backend.service.impl;

import java.util.List;

import newb.c.backend.dao.UserMapper;
import newb.c.backend.model.UserTrin;
import newb.c.backend.model.basemodel.User;
import newb.c.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository("UserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Cacheable(value="userCache")
	public User getUserById(int id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
//	@Cacheable(value="guavaCache1hour")
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
