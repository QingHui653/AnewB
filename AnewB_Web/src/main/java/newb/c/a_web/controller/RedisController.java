package newb.c.a_web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import newb.c.a_spring.backend.redis.service.WorkService;
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
import newb.c.a_spring.backend.sql.model.basemodel.User;
import newb.c.a_web.controller.component.RedisUtil;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("redis")
public class RedisController {
	//redis
	@Autowired(required=false)
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private RedisUtil redis;
	@Autowired
	private WorkService workService;
	
	
	@RequestMapping(value="redisTime",method=RequestMethod.GET)
	@ApiOperation("redis 有效时间")
	public void redisTime() throws Exception {
		//先存储到redis中然后设置有效时间
		redisTemplate.opsForValue().set("30s", "设置有效时间30s");
		redisTemplate.expire("30s", 30000, TimeUnit.MILLISECONDS);
		String timeStr = (String) redisTemplate.opsForValue().get("30s");
		//-2 未找到 key
		Long time = redisTemplate.getExpire("30s");
		System.out.println(" "+timeStr +" "+time);
		Thread.sleep(5000L);
		timeStr = (String) redisTemplate.opsForValue().get("30s");
		time = redisTemplate.getExpire("30s");
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
		valueOper.set("user:", gson.toJson(u));
		User u2 = gson.fromJson((String) valueOper.get("user"), User.class);
		System.out.println( " user "+ u2.toString());
    }

	@RequestMapping(value="/saveRedisToFolder",method=RequestMethod.GET)
	@ApiOperation("测试存储Java对象 到redis 文件夹")
	public void addRedisToFolder() {
		ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();

		User u =null;
		for (int i = 0; i < 10; i++) {
			u=new User(i,String.valueOf(i),String.valueOf(i));
			//使用冒号 ： 生成文件夹
			valueOper.set("user:"+i, JSON.toJSONString(u));
		}

	}

    @RequestMapping(value="/delRedis",method=RequestMethod.GET)
    @ApiOperation("删除 redis ")
    public void delRedis(String key) {
        redisTemplate.delete(key);
    }

    @RequestMapping(value="/updateRedis",method=RequestMethod.GET)
    @ApiOperation("更新 redis ")
    public void updateRedis() {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        valueOper.set("2","更新到测试中文2");
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
	
	@ApiOperation("redis 分布式锁")
	@RequestMapping(value = "lock",method = RequestMethod.GET)
	public void setNX() {
		boolean ifLock = redisTemplate.boundValueOps("lock").setIfAbsent("true");
		
		if(ifLock) {
			//已经设置锁进行操作
			System.out.println("获取到锁");
			Object value = redisTemplate.boundValueOps("lock").get();
			System.out.println(value);
			for (int i = 0,j=10; i< j; i++) {
				System.out.println("--- "+i);
			}
			redisTemplate.delete("lock");
			System.out.println("释放锁");
		}else {
			System.out.println("没获取到锁");
		}
		
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

	@RequestMapping(value="/addWork",method=RequestMethod.GET)
	@ApiOperation("测试jpa redis save")
	public void addWork() {
	    workService.addWork();
	}

	@RequestMapping(value="/findAllWork",method=RequestMethod.GET)
	@ApiOperation("测试 jpa redis findall")
	@ResponseBody
	public Object findAllWork() {
		return workService.findAll();
	}
	
}
