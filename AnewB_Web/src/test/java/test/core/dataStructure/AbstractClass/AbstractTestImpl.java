package test.core.dataStructure.AbstractClass;

public class AbstractTestImpl extends AbstractTest {
    @Override
    protected boolean isController() {
        System.out.println("在子类中 查找 @Controller注解");
        return true;
    }
}
