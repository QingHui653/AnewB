package newb.c.a_spring.backend.sql.dao;

import java.util.List;

import newb.c.a_spring.backend.sql.model.RepList;
import newb.c.a_spring.backend.sql.service.common.MyMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import newb.c.a_spring.backend.sql.model.basemodel.Result;

@Repository
public interface ResultMapper extends MyMapper<Result> {
	@Select("SELECT F_1 result ,count(F_1) count FROM result GROUP BY F_1")
	List<RepList> getRepList();
	
	
}