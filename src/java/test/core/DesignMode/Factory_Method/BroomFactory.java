package test.core.DesignMode.Factory_Method;

//具体工厂
public class BroomFactory extends VehicleFactory {
 public Moveable create() {
     return new Broom();
 }
}
