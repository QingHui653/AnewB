package newb.c.a_spring.backend.sql.service.impl;

import java.util.List;

import newb.c.a_spring.backend.sql.model.basemodel.UserCache;
import newb.c.a_spring.backend.sql.service.TestCacheService;
import newb.c.a_spring.backend.sql.dao.UserCacheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("TestService")
public class TestServiceImpl extends BaseServiceImpl<UserCache> implements TestCacheService {

	@Autowired
	private UserCacheMapper userCacheMapper;
		
	@Override
	@Cacheable(value="default")
	public String defaultCache(String name) {
		System.err.println("db start break defaultCache");
		return "defaultCache";
	}
	
	@Override
	@Cacheable(value="guavaCache60seconds")
	public String guavaCache60seconds(String name) {
		System.err.println("db start break guavaCache60seconds");
		return "guavaCache60seconds";
	}


	@Override
	@Cacheable(value="guavaCache10minutes")
	public String guavaCache10minutes(String name) {
		System.err.println("db start break guavaCache10minutes");
		return "guavaCache10minutes";
	}

	@Override
	@Cacheable(value="guavaCache1hour")
	public String guavaCache1hour(String name) {
		System.err.println("db start break guavaCache1hour");
		return "guavaCache1hour";
	}

	@Override
	@Cacheable(value="redisCache60seconds")
	public String redisCache60seconds(String name) {
		System.err.println("db start break redisCache60seconds");
		return "redisCache60seconds";
	}

	@Override
	@Cacheable(value="redisCache10minutes")
	public String redisCache10minutes(String name) {
		System.err.println("db start break redisCache10minutes");
		return "redisCache10minutes";
	}

	@Override
	@Cacheable(value="redisCache1hour")
	public String redisCache1hour(String name) {
		System.err.println("db start break redisCache1hour");
		return "redisCache1hour";
	}

	@Override
	public int insertAll(List<UserCache> userCacheList) {
		return userCacheMapper.insertAll(userCacheList);
	}

}
