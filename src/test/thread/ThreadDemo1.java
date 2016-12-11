package test.thread;

public class ThreadDemo1 {
	 
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1("zifangsky");
        Demo1 demo2 = new Demo1("admin");
        
        demo1.start();
        demo2.start();
    }
 
}
 
class Demo1 extends Thread{
    private String name;
    public Demo1(String name){
        this.name = name;
    }
    public void run(){
        System.out.println("Hi," + name + "欢迎您的访问");
        
    }
    
}
