package newb.c.service;

import java.util.List;

import newb.c.model.Result;

public interface ResultService extends IService<Result> {  
	
	Result getOneById(int id);  
    
    List<Result> getAll();
}
