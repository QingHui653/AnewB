package newb.c.a_spring.backend.sql.service.impl;

import newb.c.a_spring.backend.sql.dao.TOrderMapper;
import newb.c.a_spring.backend.sql.model.basemodel.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import newb.c.a_spring.backend.sql.service.TOrderService;

@Service("TOrderService")
public class TOrderServiceImpl extends BaseServiceImpl<TOrder> implements TOrderService{
	
	@Autowired
	private TOrderMapper mapper;
	@Override
	public int insertByMapper(TOrder model) {
		return mapper.insertByMapper(model);
	}
	@Override
	public int insertByXML(TOrder model) {
		return mapper.insertByXML(model);
	}
	@Override
	public int insertByComm(TOrder model) {
		return mapper.insert(model);
	}

	@Override
	@Transactional
	/* 通过主键重复可以测试回滚，数据没问题，正常提交不同数据库 */
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
