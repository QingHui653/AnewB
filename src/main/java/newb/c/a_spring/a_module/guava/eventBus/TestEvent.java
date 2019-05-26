package newb.c.a_spring.a_module.guava.eventBus;

import lombok.Data;

/**
 * @Auther:woshizbh
 * @Date: 2018/10/15
 * @Deseription
 */
@Data
public class TestEvent {
    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }
}