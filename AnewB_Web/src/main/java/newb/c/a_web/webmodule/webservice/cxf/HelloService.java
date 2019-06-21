package newb.c.a_web.webmodule.webservice.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloService{
	
  public String sayHello(@WebParam(name="text") String name);
  
}
