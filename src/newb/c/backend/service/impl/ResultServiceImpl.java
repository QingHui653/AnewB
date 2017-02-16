package newb.c.backend.service.impl;

import java.util.List;

import newb.c.backend.dao.ResultMapper;
import newb.c.backend.model.RepList;
import newb.c.backend.model.basemodel.Result;
import newb.c.backend.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository("ResultService")
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
