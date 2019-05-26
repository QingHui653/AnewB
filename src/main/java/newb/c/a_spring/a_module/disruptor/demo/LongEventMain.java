package newb.c.a_spring.a_module.disruptor.demo;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Auther:b
 * @Date: 2018/9/29
 * @Deseription
 */
public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {
        new LongEventMain().java7();
    }

    @Test
    public void java7() throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, executor);

//Disruptor<LongEvent> disruptor = new Disruptor(
//            factory, bufferSize, ProducerType.SINGLE, new BlockingWaitStrategy(), executor);

        Disruptor<LongEvent> disruptor = new Disruptor<>(factory,bufferSize,executor,ProducerType.SINGLE, new BlockingWaitStrategy());
        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(10);
        }
    }

    @Test
    public void java8() throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);

        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new,bufferSize,executor,ProducerType.SINGLE, new BlockingWaitStrategy());

        // Connect the handler
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event));

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
            Thread.sleep(10);
        }
    }
}
