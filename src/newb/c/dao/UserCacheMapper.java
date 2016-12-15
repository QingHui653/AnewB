package newb.c.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import newb.c.model.UserCache;
import newb.c.service.common.MyMapper;

/*@Repository*/
public interface UserCacheMapper extends MyMapper<UserCache> {
	
	int insertAll(List<UserCache> userCacheList);
}