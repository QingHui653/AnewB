package newb.c.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import newb.c.backend.model.basemodel.User;
import newb.c.controller.component.RedisUtil;

@Controller
@RequestMapping("redis")
public class RedisController {
	//redis
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private RedisUtil redis;
	
	
	@RequestMapping(value="redisTime",method=RequestMethod.GET)
	@ApiOperation("redis 有效时间")
	public void redisTime() throws Exception {
		//先存储到redis中然后设置有效时间
		redisTemplate.opsForValue().set("+5s", "5s");
		redisTemplate.expire("+5s", 5000, TimeUnit.MILLISECONDS);
		String timeStr = (String) redisTemplate.opsForValue().get("+5s");
		Long time = redisTemplate.getExpire("+5s");
		System.out.println(" "+timeStr +" "+time);
		
		Thread.sleep(5000l);
		
		timeStr = (String) redisTemplate.opsForValue().get("+5s");
		time = redisTemplate.getExpire("+5s");
		System.out.println(" "+timeStr +" "+time);
		
		System.out.println("--测试有效时间OK--- ");
	}
	
	@RequestMapping(value="redisQueue",method=RequestMethod.GET)
	@ApiOperation("redis 队列")
	public void redisQueue() throws Exception {
		//发送频道到 java
		redisTemplate.convertAndSend("java", "java发布的消息");
		System.out.println(" --发送队列消息成功--- ");
	}
	
	@RequestMapping(value="/saveRedis",method=RequestMethod.GET)
	@ApiOperation("测试存储Java对象到redis")
	public void addRedis() {
		Gson gson = new Gson();
		User u= new User(1, "1", "1");
		ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
		valueOper.set("2", "测试中文");
		System.out.println("redis 查询:"+valueOper.get("2"));
		//存储对象,有三种，序列化二进制， 序列化json，序列化map
		valueOper.set("user", gson.toJson(u));
		User u2 = gson.fromJson((String) valueOper.get("user"), User.class);
		System.out.println( " user "+ u2.toString());
    }
	
	@RequestMapping(value="/saveRedisList",method=RequestMethod.GET)
	@ApiOperation("测试存储List 到redis")
	public void addRedisList() {
		//添加 一个 list 列表
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush("lpList", "lp");
        list.rightPush("lpList", "26");
        //输出 list
        System.out.println(list.range("lpList", 0, 1));
    }
	
	@RequestMapping(value="/saveRedisHash",method=RequestMethod.GET)
	@ApiOperation("测试存储Hash 到redis")
	public void addRedisHash() {
		//添加 一个 hash集合
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "lp");
        map.put("age", "26");
        hash.putAll("lpMap", map);
        //获取 map
        System.out.println(hash.entries("lpMap"));
    }
	
	@RequestMapping(value="/saveRedisSet",method=RequestMethod.GET)
	@ApiOperation("测试存储Set 到redis")
	public void addRedisSet() {
		 //添加 一个 set 集合
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add("lpSet", "lp");
        set.add("lpSet", "26");
        set.add("lpSet", "178cm");
        //输出 set 集合
        System.out.println(set.members("lpSet"));
    }
	
	@RequestMapping(value="/saveRedisZSet",method=RequestMethod.GET)
	@ApiOperation("测试存储ZSet 到redis")
	public void addRedisZSet() {
		//添加有序的 set 集合
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add("lpZset", "lp", 0);
        zset.add("lpZset", "26", 1);
        zset.add("lpZset", "178cm", 2);
        //输出有序 set 集合
        System.out.println(zset.rangeByScore("lpZset", 0, 2));
    }
	
	@RequestMapping(value="/saveRedisCluster",method=RequestMethod.GET)
	@ApiOperation("测试 Cluster  redis 暂无")
	public void addRedisCluster() {
		// redis Cluster集群时使用
        ClusterOperations<String, Object> cluster = redisTemplate.opsForCluster();
    }
	
	@RequestMapping(value="/saveRedisHyper",method=RequestMethod.GET)
	@ApiOperation("测试 Hyper  redis 暂无")
	public void addRedisHyper() {
		// redis存大数据  会调用压缩算法
        HyperLogLogOperations<String, Object> Hyper = redisTemplate.opsForHyperLogLog();
    }
	
	@RequestMapping(value="/saveRedisGeo",method=RequestMethod.GET)
	@ApiOperation("测试 Geo  redis 暂无")
	public void addRedisGeo() {
		// 地理位置
        GeoOperations<String, Object> Geo = redisTemplate.opsForGeo();
    }
	
}
