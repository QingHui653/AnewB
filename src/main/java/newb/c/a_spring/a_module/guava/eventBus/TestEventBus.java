package newb.c.a_spring.a_module.guava.eventBus;

import com.google.common.base.Optional;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Auther:woshizb
 * @Date: 2018/10/15
 * @Deseription
 */
public class TestEventBus {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();
        EventListener2 listener2 = new EventListener2();

        eventBus.register(listener);
        eventBus.register(listener2);

//        eventBus.post(new TestEvent(200));
//        eventBus.post(130l);
//        eventBus.post(new TestEvent(300));
//        eventBus.post(new TestEvent(400));
        eventBus.post(new BigDecimal(3));
    }
}
