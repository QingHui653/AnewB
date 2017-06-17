package newb.c.webmodule.webservice.cxf;


public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello"+name;
	}

}
