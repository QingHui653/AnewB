package newb.c.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import newb.c.model.RepList;
import newb.c.model.Result;
import newb.c.service.common.MyMapper;

public interface ResultMapper extends MyMapper<Result> {
	
	@Select("SELECT F_1,count(F_1) num FROM `result` GROUP BY F_1")
	List<RepList> getRepList();
}