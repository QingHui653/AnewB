package newb.c.service;

import java.util.List;

import newb.c.model.UserCache;

public interface TestCacheService extends IService<UserCache>{
	
	String defaultCache(String name);
	
	String guavaCache60seconds(String name);
	String guavaCache10minutes(String name);
	String guavaCache1hour(String name);
	
	String redisCache60seconds(String name);
	String redisCache10minutes(String name);
	String redisCache1hour(String name);
	
	int insertAll(List<UserCache> userCacheList);
}
