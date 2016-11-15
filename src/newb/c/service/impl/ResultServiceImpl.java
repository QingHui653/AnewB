package newb.c.service.impl;

import java.util.List;

import newb.c.dao.ResultMapper;
import newb.c.model.RepList;
import newb.c.model.Result;
import newb.c.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

}
