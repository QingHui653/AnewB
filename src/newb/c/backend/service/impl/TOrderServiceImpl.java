package newb.c.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import newb.c.backend.dao.TOrderMapper;
import newb.c.backend.model.basemodel.TOrder;
import newb.c.backend.service.TOrderService;

@Service("TOrderService")
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

	@Override
	@Transactional
	public int testJTA() {
		TOrder t2= new TOrder(3,4 , "進入newb t_order_1"); //偶数进newb t_order_1
		TOrder t1= new TOrder(4,3 , "進入newb2 t_order_0"); //奇数进newb2 t_order_0
		mapper.insert(t2);
		mapper.insert(t1);
		mapper.insert(t2);
		return 0;
	}
	
	@Override
	@Transactional
	public int testOneData() {
		TOrder t2= new TOrder(3,4 , "進入newb t_order_1"); //偶数进newb t_order_1
		mapper.insert(t2);
		mapper.insert(t2);
		return 0;
	}
}
