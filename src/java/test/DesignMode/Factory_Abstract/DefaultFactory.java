package test.DesignMode.Factory_Abstract;

//具体工厂类，其中Food,Vehicle，Weapon是抽象类，
public class DefaultFactory extends AbstractFactory{
  @Override
  public Food createFood() {
      return new Apple();
  }
  @Override
  public Vehicle createVehicle() {
      return new Car();
  }
  @Override
  public Weapon createWeapon() {
      return new AK47();
  }
}
