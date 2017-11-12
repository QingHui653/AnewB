package newb.c.a_spring.a_config.springBean;

public class ProcessBean {

    public ProcessBean() {
        System.out.println("进入构造方法 实例化");
    }

    private String username;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
