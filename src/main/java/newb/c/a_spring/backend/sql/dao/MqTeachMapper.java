package newb.c.a_spring.backend.sql.dao;

import newb.c.a_spring.backend.sql.model.basemodel.MqTeach;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MqTeachMapper extends Mapper<MqTeach> {
}