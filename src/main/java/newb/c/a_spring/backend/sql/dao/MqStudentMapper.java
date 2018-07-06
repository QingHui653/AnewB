package newb.c.a_spring.backend.sql.dao;

import newb.c.a_spring.backend.sql.model.basemodel.MqStudent;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MqStudentMapper extends Mapper<MqStudent> {
}