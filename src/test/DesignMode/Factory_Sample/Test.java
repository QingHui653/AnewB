package test.DesignMode.Factory_Sample;

/**
 * 简单工厂模式
 * @author woshizbh
 *
 */
public class Test {

    public static void main(String[] args) {
        Car c = Factory.getCarInstance("Benz");
        if (c != null) {
            c.run();
            c.stop();
        } else {
            System.out.println("造不了这种汽车。。。");
        }

    }

}
