package newb.c.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import newb.c.model.TOrder;
import newb.c.service.common.MyMapper;

public interface TOrderMapper extends MyMapper<TOrder> {
	
	@Insert("insert into t_order(order_id,user_id,status) vaules(#{orderId},#{userId},#{status})")
	int insertByMapper(TOrder model);
	
	int insertByXML(TOrder model);
	
}