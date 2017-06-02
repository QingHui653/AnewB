package test.DesignMode.Decorate;

/**
 * 装饰者模式
 * @author woshizbh
 *
 */
public class Test {
    public static void main(String[] args) {
        Food food = new Bread(new Vegetable(new Cream(new Food("香肠"))));
        Food food2 = new Bread(new Cream(new Food("香肠")));
        System.out.println(food.make());
        System.out.println(food2.make());
    }
}
