package newb.c.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import newb.c.backend.model.basemodel.User;
import newb.c.backend.model.UserData;
import newb.c.backend.model.UserTrin;
import newb.c.backend.service.common.MyMapper;

/**
 *  使用了通用mapper插件
 * @author woshizbh
 *
 */
/*@Repository*/
public interface UserMapper extends MyMapper<User> {
	
	@Select("select * from user where oid=#{id}")
    User testSelectByKey(int id);
	
	int insertAll(List<User> userList);
	
	@Select("select * from user where oid>=0 for update")
	/*@Options(useCache = false,timeout = 10000,flushCache = false)
    @ResultMap("BaseResultMap")*/
	List<User> selectAllForUpdate();
	
	String selectPW(int oid);
	
	UserTrin selectUserAndUserCacheByUser(int oid);
	
	@Select("select id,name,age,user.oid,user.username,user.password from user_cache left join user on user_cache.id=user.oid where user_cache.id=#{oid}")
	UserTrin selectUserAndUserCacheByDao(int oid);
}