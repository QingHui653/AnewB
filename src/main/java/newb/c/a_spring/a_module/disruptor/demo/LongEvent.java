package newb.c.a_spring.a_module.disruptor.demo;

/**
 * @Auther:b
 * @Date: 2018/9/29
 * @Deseription
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
