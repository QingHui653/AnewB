package newb.c.dao;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import newb.c.model.User;
import newb.c.model.UserData;
import newb.c.service.common.MyMapper;

/**
 *  使用了通用mapper插件
 * @author woshizbh
 *
 */
public interface UserMapper extends MyMapper<User> {
	
	@Select("select * from user where okid=#{id}")
    User testSelectByKey(int id);
}