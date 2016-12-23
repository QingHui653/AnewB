package newb.c.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import newb.c.dao.TOrderMapper;
import newb.c.model.TOrder;
import newb.c.service.TOrderService;

@Service("TOrderService")
public class TOrderServiceImpl extends BaseServiceImpl<TOrder> implements TOrderService{
	
	@Autowired
	private TOrderMapper mapper;
	
	public int insertByMapper(TOrder model) {
		return mapper.insert(model);
	}
	
	public int insertByXML(TOrder model) {
		return mapper.insertByXML(model);
	}
	
	public int insertByComm(TOrder model) {
		return mapper.insert(model);
	}
}
