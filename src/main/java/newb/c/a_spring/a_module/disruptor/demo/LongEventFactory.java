package newb.c.a_spring.a_module.disruptor.demo;

import com.lmax.disruptor.EventFactory;

/**
 * @Auther:woshizbh
 * @Date: 2018/9/29
 * @Deseription
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
