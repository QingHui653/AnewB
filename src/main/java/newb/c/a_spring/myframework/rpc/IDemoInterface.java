package newb.c.a_spring.myframework.rpc;

public interface IDemoInterface {
	
	public String withReturn();
    public String withReturn(String name);
    public void noReturn(String name);
    public String noArgument();
}
