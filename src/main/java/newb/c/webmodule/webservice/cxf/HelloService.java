package newb.c.webmodule.webservice.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloService{
	
  public String sayHello(@WebParam(name="text") String name);
  
}
