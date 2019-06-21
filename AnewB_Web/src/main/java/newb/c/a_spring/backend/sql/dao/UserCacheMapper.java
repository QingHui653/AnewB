package newb.c.a_spring.backend.sql.dao;

import newb.c.a_spring.backend.sql.model.basemodel.UserCache;
import newb.c.a_spring.backend.sql.service.common.MyMapper;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheMapper extends MyMapper<UserCache> {
	
	int insertAll(List<UserCache> userCacheList);
}