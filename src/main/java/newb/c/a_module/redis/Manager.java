/**
 * @author woshizbh
 *
 */
package newb.c.a_module.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Manager {

	private String url = "192.168.1.188";

	private Jedis jedis;

	@Before
	public void connRedis() {
		// 连接本地的 Redis 服务
		jedis = new Jedis(url);
		System.out.println("连接成功");
		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
	}

	// @Test
	public void conn() {
		// 连接本地的 Redis 服务
		jedis = new Jedis(url);
		System.out.println("连接成功");
		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
	}

//	@Test
	public void RedisStringJava() {
		// 设置 redis 字符串数据
		jedis.set("runoobkey", "www.runoob.com");
		// 获取存储的数据并输出
		System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
	}
	
	@Test
	public void RedisListJava() {
		//存储数据到列表中 
		//使用：冒号表示存储到文件夹中
        jedis.lpush("site-list:list", "Runoob");
        jedis.lpush("site-list:list", "Google");
        jedis.lpush("site-list:list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list:list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("文件夹列表项为: "+list.get(i));
        }
        
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list2 = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list2.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }

	}
	
//	@Test
	public void find() {
		// 获取数据并输出
        Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }
	}
}
