package newb.c.backend.sql.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import newb.c.backend.sql.dao.UserMapper;
import newb.c.backend.sql.model.UserTrin;
import newb.c.backend.sql.model.basemodel.User;
import newb.c.backend.sql.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	private volatile int threadCount =0;

	private Lock lock =new ReentrantLock();

	@Override
//	@Cacheable(value="userCache")
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
//	@Cacheable(value="default")
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

	@Override
	public String testMock(String name) {
		return name;
	}

	@Override
	public int threadInsertAll(int page) {
		threadCount=0;
		ExecutorService excutor= Executors.newCachedThreadPool();

			for (int i = 0; i < page; i++) {
				excutor.execute(new Runnable() {

					@Override
					public void run() {
							lock.lock();
							threadCount++;
							lock.unlock();
							List<User> userList = new ArrayList<User>();
							userList = threadList(threadCount);
							userMapper.insertAll(userList);
					}
				});
			}
		return 0;
	}

	private List<User> threadList(int page) {
		List<User> userList = new ArrayList<User>();
		for (int i =1000*page ; i <= 1000*page+999; i++) {
			User user = new User();
			user.setOid(i);
			user.setUsername(i + "");
			user.setPassword(i + "");
			userList.add(user);
		}
		return userList;
	}
}
