package test.core.dataStructure.AbstractClass;

public abstract class AbstractTest {

    protected abstract boolean isController();

    protected void initHandlerMethods() {
        System.out.println("查找 Controller Mapping");

        if(isController()){
            detectHandlerMethods();
        }
    }

    protected void detectHandlerMethods() {
        System.out.println("检测 Controller");
        registerHandlerMethod();
    }

    protected void registerHandlerMethod() {
        System.out.println("注册 Controller");
    }
}
