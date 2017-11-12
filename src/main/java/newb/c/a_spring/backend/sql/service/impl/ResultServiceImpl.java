package newb.c.a_spring.backend.sql.service.impl;

import java.util.ArrayList;
import java.util.List;

import newb.c.a_spring.backend.sql.dao.ResultMapper;
import newb.c.a_spring.backend.sql.model.RepList;
import newb.c.a_spring.backend.sql.model.basemodel.Result;
import newb.c.a_spring.backend.sql.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ResultService")
public class ResultServiceImpl extends BaseServiceImpl<Result> implements ResultService {
	
	@Autowired
	private ResultMapper resultMapper;
	
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
