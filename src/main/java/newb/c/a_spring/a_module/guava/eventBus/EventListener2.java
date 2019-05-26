package newb.c.a_spring.a_module.guava.eventBus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @Auther:woshizbh
 * @Date: 2018/10/15
 * @Deseription
 */
public class EventListener2 {
    public int lastMessage = 0;

    // 观察者 收到信息 执行
    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message2:"+lastMessage);
    }

    // 发送的 信息 都不是 观察者 需要的 信息时.
    @Subscribe
    public void listen(DeadEvent event) {
        System.out.println("none event");
    }

    // 具有 继承关系
    // 如果Listener A监听Event A, 而Event A有一个子类Event B, 此时Listener A将同时接收Event A和B消息，实例如下：

    public int getLastMessage() {
        return lastMessage;
    }
}
