package newb.c.controller.TestCache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import newb.c.model.UserCache;
import newb.c.service.TestCacheService;

@Service("TestService")
public class TestServiceImpl extends BaseService<UserCache> implements TestCacheService{

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

}
