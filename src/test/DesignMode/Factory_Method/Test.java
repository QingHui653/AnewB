package test.DesignMode.Factory_Method;

//测试类
public class Test {
 public static void main(String[] args) {
     VehicleFactory factory = new BroomFactory();
     Moveable m = factory.create();
     m.run();
 }
}
