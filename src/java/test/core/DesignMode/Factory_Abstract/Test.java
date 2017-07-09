package test.core.DesignMode.Factory_Abstract;

//测试类
/**
 * 抽象工厂模式
 * @author woshizbh
 *
 */
public class Test {
  public static void main(String[] args) {
      AbstractFactory f = new DefaultFactory();
      Vehicle v = f.createVehicle();
      v.run();
      Weapon w = f.createWeapon();
      w.shoot();
      Food a = f.createFood();
      a.printName();
  }
}
