package newb.c.a_spring.a_module.disruptor.demo;

import com.lmax.disruptor.EventHandler;

/**
 * @Auther:woshizbh
 * @Date: 2018/9/29
 * @Deseription
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event "+ event.toString());
    }
}
