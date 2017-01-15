package newb.c.webservice.cxf;

import javax.jws.WebService;

@WebService
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello"+name;
	}

}
