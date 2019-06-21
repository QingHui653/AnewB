package test.core.DesignMode.Adapter;

/**
 * 适配器模式
 * @author woshizbh
 *
 */
public class Adapter {
    public static void main(String[] args) {
        Phone phone = new Phone();
        VoltageAdapter adapter = new VoltageAdapter();
        phone.setAdapter(adapter);
        phone.charge();
    }
}

// 手机类
class Phone {

    public static final int V = 220;// 正常电压220v，是一个常量

    //适配器
    private VoltageAdapter adapter;

    //充电
    public void charge() {
        adapter.changeVoltage();
    }

    //增加个适配器
    public void setAdapter(VoltageAdapter adapter) {
        this.adapter = adapter;
    }
}

// 变压器
class VoltageAdapter {
    // 改变电压的功能
    public void changeVoltage() {
        System.out.println("正在充电...");
        System.out.println("原始电压：" + Phone.V + "V");
        System.out.println("经过变压器转换之后的电压:" + (Phone.V - 200) + "V");
    }
}
