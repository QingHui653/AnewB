package newb.c.backend.service;

import newb.c.backend.model.basemodel.TOrder;

public interface TOrderService extends IService<TOrder>{
	int insertByMapper(TOrder model);
	
	int insertByXML(TOrder model);
	
	int insertByComm(TOrder model);
	
	int testJTA();

	int testOneData();
}
