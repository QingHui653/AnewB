package newb.c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import newb.c.backend.model.basemodel.User;

@Controller
@RequestMapping("redis")
public class RedisController {
	//redis
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@RequestMapping(value="/saveRedis",method=RequestMethod.GET)
	@ApiOperation("测试存储Java对象到redis")
	public void addRedis() {
		Gson gson = new Gson();
		User u= new User(1, "1", "1");
		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
		valueOper.set("2", "测试中文");
		System.out.println("redis 查询"+valueOper.get("2"));
		
		//存储对象,有三种，序列化二进制， 序列化json，序列化map
		valueOper.set("user", gson.toJson(u));
		User u2 = gson.fromJson(valueOper.get("user"), User.class);
		System.out.println( " user "+ u2.toString());
    }
	
	@RequestMapping(value="redisQueue",method=RequestMethod.GET)
	@ApiOperation("redis 队列")
	public void redisQueue() throws Exception {
		//发送频道到 java
		redisTemplate.convertAndSend("java", "java发布的消息");
		
		System.out.println(" --发送队列消息成功--- ");
	}
}
