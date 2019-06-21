package newb.c.a_spring.a_module.guava.eventBus;

import com.google.common.eventbus.Subscribe;

/**
 * @Auther:woshizbh
 * @Date: 2018/10/15
 * @Deseription
 */
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message1:"+lastMessage);
    }

    @Subscribe
    public void listen(Long event) {
        System.out.println("Message1 long:"+event);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
