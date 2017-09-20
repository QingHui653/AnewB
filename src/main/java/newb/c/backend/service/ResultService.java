package newb.c.backend.service;

import java.util.List;

import newb.c.backend.model.RepList;
import newb.c.backend.model.basemodel.Result;

public interface ResultService extends IService<Result> {  
	
	Result getOneById(int id);  
    
    List<Result> getAll();
    
    List<RepList> getRepList();

    Object tranTest();
}
