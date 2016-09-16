package newb.c.dao;

import newb.c.model.User;
import newb.c.util.MyMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 *  使用了通用mapper插件
 * @author woshizbh
 *
 */
public interface UserMapper extends MyMapper<User> {
	
}