package newb.c.a_spring.a_module.spi.service.impl;

import newb.c.a_spring.a_module.spi.service.HelloService;

public class Demo2ServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "Demo2";
    }
}
