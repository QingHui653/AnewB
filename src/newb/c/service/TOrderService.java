package newb.c.service;

import newb.c.model.TOrder;

public interface TOrderService extends IService<TOrder>{
	int insertByMapper(TOrder model);
	
	int insertByXML(TOrder model);
	
	int insertByComm(TOrder model);
}
