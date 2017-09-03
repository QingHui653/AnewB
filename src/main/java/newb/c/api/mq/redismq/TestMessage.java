package newb.c.api.mq.redismq;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * redis消息监听类
 * @author woshizbh
 *
 */
public class TestMessage {
	
    public void handleMessage(Serializable message) {
    	System.out.println("  -----进入redis 消息监听器----- ");
        if (message == null) {
            System.out.println("null");
        } else if (message.getClass().isArray()) {
            System.out.println(Arrays.toString((Object[]) message));
        } else if (message instanceof List<?>) {
            System.out.println(message);
        } else if (message instanceof Map<?, ?>) {
            System.out.println(message);
        } else {
            System.out.println(message);
        }
    }
}
