package newb.c.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import newb.c.model.User;
import newb.c.model.UserData;
import newb.c.service.common.MyMapper;

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
	List<User> selectAllForUpdate();
	
	String selectPW(int oid);
}