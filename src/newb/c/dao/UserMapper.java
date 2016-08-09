package newb.c.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import newb.c.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    @Select("select oid,username,password from user")
    List<User> selectAll();
}