package newb.c.util.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Pages implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int page =0;
	
	int pageSize=10;
	
	int totalSize ;
	
	int totalPage;
	
	List<?> list ;
}
