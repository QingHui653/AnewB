package newb.c.service.impl;

import java.util.List;

import newb.c.model.Result;
import newb.c.service.ResultService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("ResultService")
public class ResultServiceImpl extends BaseService<Result> implements ResultService {
	
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

}
