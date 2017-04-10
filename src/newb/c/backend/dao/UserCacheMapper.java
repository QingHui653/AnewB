package newb.c.backend.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import newb.c.backend.model.basemodel.UserCache;
import newb.c.backend.service.common.MyMapper;

@Repository
public interface UserCacheMapper extends MyMapper<UserCache> {
	
	int insertAll(List<UserCache> userCacheList);
}