package newb.c.api.log;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import redis.clients.jedis.Jedis;

public class logAppender  extends AppenderSkeleton {
	
	private String redisHost;
	
	private int redisPort;
	
	private Jedis jedis;
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void append(LoggingEvent event) {
		GregorianCalendar cal= new GregorianCalendar();
		if(jedis==null){
			jedis = new Jedis(redisHost,redisPort);
		}
//		System.out.println("  连接redis成功");
		jedis.set(sdf.format(cal.getTime()), event.getMessage().toString());
	}
	
	@Override
	public void close() {
		System.out.println(" 关闭log入库");
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

}
