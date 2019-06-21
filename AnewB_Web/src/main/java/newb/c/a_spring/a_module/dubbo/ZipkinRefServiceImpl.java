package newb.c.a_spring.a_module.dubbo;

public class ZipkinRefServiceImpl implements ZipkinRefService {

    private DemoService demoService;

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String greeting(String name) {
        System.out.println("进入 zipKinService 服务");
        System.out.println("调用 demoService 服务");
        System.out.println(demoService.sayHello("从zipkin 进入"));
        return "OK";
    }
}
