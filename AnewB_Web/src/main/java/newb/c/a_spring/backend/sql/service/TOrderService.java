package newb.c.a_spring.backend.sql.service;

import newb.c.a_spring.backend.sql.model.basemodel.TOrder;

public interface TOrderService extends IService<TOrder>{
	int insertByMapper(TOrder model);
	
	int insertByXML(TOrder model);
	
	int insertByComm(TOrder model);
	
	int testJTA();

	int testOneData();
}
