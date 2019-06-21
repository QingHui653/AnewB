package newb.c.a_spring.backend.sql.dao;

import java.util.List;

import newb.c.a_spring.backend.sql.model.UserTrin;
import newb.c.a_spring.backend.sql.model.basemodel.User;
import newb.c.a_spring.backend.sql.service.common.MyMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *  使用了通用mapper插件
 * @author woshizbh
 *
 */
@Repository
public interface UserMapper extends MyMapper<User> {

	@Select("select * from user where oid=#{id}")
    User testSelectByKey(int id);

	int insertAll(List<User> userList);

//	@Select("select * from user where oid>=0 for update")
	@Select("select * from user where oid>=0")
	/*@Options(useCache = false,timeout = 10000,flushCache = false)
    @ResultMap("BaseResultMap")*/
	List<User> selectAllForUpdate();

	String selectPW(int oid);

	UserTrin selectUserAndUserCacheByUser(int oid);

	@Select("select id,name,age,user.oid,user.username,user.password from user_cache left join user on user_cache.id=user.oid where user_cache.id=#{oid}")
	UserTrin selectUserAndUserCacheByDao(int oid);
}