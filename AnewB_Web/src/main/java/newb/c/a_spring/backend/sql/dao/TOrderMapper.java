package newb.c.a_spring.backend.sql.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import newb.c.a_spring.backend.sql.model.basemodel.TOrder;
import newb.c.a_spring.backend.sql.service.common.MyMapper;

@Repository
public interface TOrderMapper extends MyMapper<TOrder> {
	
	@Insert("insert into t_order(order_id,user_id,status) vaules(#{orderId},#{userId},#{status})")
	int insertByMapper(TOrder model);
	
	int insertByXML(TOrder model);
	
}