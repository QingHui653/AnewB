package test.arrays;

import java.util.Comparator;

import newb.c.backend.model.basemodel.User;

public class ComparatorDemo implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		User temp1 = (User) o1;  
    	User temp2 = (User) o2;  
      
    	if(temp1.getOid()>temp2.getOid())return 1;  
    	else if(temp1.getOid()<temp2.getOid())return -1;  
    	else if(temp1.getOid().equals(temp2.getOid()))return 0;  
    	return 0;
	}


}
