package newb.c.a_spring.a_module.spi.service;

import org.junit.Test;

import java.util.ServiceLoader;

public class spiTest {
    public static void main(String[] args) {
        ServiceLoader<HelloService> helloServiceLoader=ServiceLoader.load(HelloService.class);
        for(HelloService item:helloServiceLoader){
            item.hello();
        }
    }
    /**
     * spi配置文件 配置在 加载类路径下/META-INF/services/接口全称
     */
    @Test
    public void test(){
        ServiceLoader<HelloService> helloServiceLoader=ServiceLoader.load(HelloService.class);
        for(HelloService item:helloServiceLoader){
            String hello = item.hello();
            System.out.println(hello);
        }
    }
}
