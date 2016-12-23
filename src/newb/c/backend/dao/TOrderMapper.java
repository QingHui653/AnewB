package newb.c.backend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import newb.c.model.TOrder;
import newb.c.service.common.MyMapper;

/*@Repository*/
public interface TOrderMapper extends MyMapper<TOrder> {
	
	@Insert("insert into t_order(order_id,user_id,status) vaules(#{orderId},#{userId},#{status})")
	int insertByMapper(TOrder model);
	
	int insertByXML(TOrder model);
	
}