package newb.c.a_spring.a_config.springBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 那么每当在一个ApplicationEvent发布到 ApplicationContext时，这个bean得到通知
 *
 * //http://blog.csdn.net/ilovejava_2010/article/details/7953419
 */
public class TestApplicationListener implements ApplicationListener {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("进入TestApplicationListener"+event.toString());
    }
}
