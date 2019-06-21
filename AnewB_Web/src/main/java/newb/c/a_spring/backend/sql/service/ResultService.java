package newb.c.a_spring.backend.sql.service;

import java.util.List;

import newb.c.a_spring.backend.sql.model.RepList;
import newb.c.a_spring.backend.sql.model.basemodel.Result;

public interface ResultService extends IService<Result> {
	
	Result getOneById(int id);  
    
    List<Result> getAll();
    
    List<RepList> getRepList();

    Object tranTest();
}
