package newb.c.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import newb.c.backend.dao.TOrderMapper;
import newb.c.backend.model.basemodel.TOrder;
import newb.c.backend.service.TOrderService;

@Repository("TOrderService")
public class TOrderServiceImpl extends BaseServiceImpl<TOrder> implements TOrderService{
	
	@Autowired
	private TOrderMapper mapper;
	
	public int insertByMapper(TOrder model) {
		return mapper.insertByMapper(model);
	}
	
	public int insertByXML(TOrder model) {
		return mapper.insertByXML(model);
	}
	
	public int insertByComm(TOrder model) {
		return mapper.insert(model);
	}
}
