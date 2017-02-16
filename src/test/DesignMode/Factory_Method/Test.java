package test.DesignMode.Factory_Method;

//测试类
/**
 * 工厂模式
 * @author woshizbh
 *
 */
public class Test {
 public static void main(String[] args) {
     VehicleFactory factory = new BroomFactory();
     Moveable m = factory.create();
     m.run();
     VehicleFactory factory2 = new PlaneFactory();
     Moveable m2 = factory2.create();
     m2.run();
 }
}
