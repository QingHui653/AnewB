package newb.c.backend.sql.dao;

import newb.c.backend.sql.model.basemodel.UserCache;
import newb.c.backend.sql.service.common.MyMapper;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheMapper extends MyMapper<UserCache> {
	
	int insertAll(List<UserCache> userCacheList);
}