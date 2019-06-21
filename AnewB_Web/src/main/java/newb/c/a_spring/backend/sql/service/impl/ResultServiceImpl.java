package newb.c.a_spring.backend.sql.service.impl;

import java.util.ArrayList;
import java.util.List;

import newb.c.a_spring.backend.sql.dao.ResultMapper;
import newb.c.a_spring.backend.sql.model.RepList;
import newb.c.a_spring.backend.sql.model.basemodel.Result;
import newb.c.a_spring.backend.sql.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service("ResultService")
public class ResultServiceImpl extends BaseServiceImpl<Result> implements ResultService {
	
	@Autowired
	private ResultMapper resultMapper;

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Override
	@Cacheable(value="default")
	public Result getOneById(int id) {
		Result reslut = mapper.selectByPrimaryKey(id);
		return reslut;
	}

	@Override
	@Cacheable(value="guavaCache60seconds")
	public List<Result> getAll() {
		return mapper.selectAll();
	}

	@Override
	public List<RepList> getRepList() {
		return resultMapper.getRepList();
	}


	@Transactional
	@Override
	public Object tranTest() {
		List<Result> list =swapList();
		for (int i=0;i<3;i++){
				mapper.insert(list.get(i));
		}
		System.out.println("插入完成");
			throw new RuntimeException("抛出异常回滚");
	}

	public void tranTest2() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("SomeTxName");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			List<Result> list =swapList();
			for (int i=0;i<3;i++){
				mapper.insert(list.get(i));
			}
			System.out.println("插入完成");
			throw new RuntimeException("抛出异常回滚");
		} catch (Exception ex) {
			transactionManager.rollback(status);
			throw ex;
		}
	}


	// 手动 事务
	public void tranTest3() {
		// 设置保存点
		Object savepoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();

		List<Result> list =swapList();
		for (int i=0;i<3;i++){
			mapper.insert(list.get(i));
		}
		System.out.println("插入完成");

		try{
			//抛出异常
			throw new RuntimeException("抛出异常回滚");
		}catch(Exception e){
			//回滚 保存点
			TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savepoint);
			e.printStackTrace();
		}
	}


	private List<Result> swapList(){
		List<Result> list =new ArrayList<>();
		Result e1 =new Result(1,"1","2","3","4","5");
		Result e2 =new Result(2,"2","2","3","4","5");
		Result e3 =new Result(3,"3","2","3","4","5");
		list.add(e1);
		list.add(e2);
		list.add(e3);
		return list;
	}
}
