package newb.c.a_spring.a_config.springBean;

import lombok.Data;

@Data
public class ProcessBean {

    public ProcessBean() {
        System.out.println("进入构造方法 实例化");
    }

    private String username;

    private String password;

}
