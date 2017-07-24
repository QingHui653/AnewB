package newb.c.a_module.mongodb;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;

import newb.c.backend.model.UserList;
import newb.c.backend.model.basemodel.User;

public class MongoTemplateUnit {
	private static final Logger log =LoggerFactory.getLogger(MongoTemplateUnit.class);
	
	private String host="119.23.231.239";
	
	private int port=27017;
	
	private String database="MongoTemplateUnit";
	
	private MongoClient mongo;
	
	private MongoTemplate mongoTemplate;
	
	@Before
	public void before() {
		mongo =new MongoClient(host, port);
		MongoDbFactory mongoDbFactory =new SimpleMongoDbFactory(mongo , database);
		mongoTemplate= new MongoTemplate(mongoDbFactory);
		
	}
	
	@After
	public void after() {
		mongo.close();
	}
	
	@Test
	public void insertList(){
		List<User> userList =new ArrayList<>();
		
		User u1 =new User(1, "1", "1");
		userList.add(u1);
		
		User u2 =new User(2, "2", "2");
		userList.add(u2);
		
		UserList userLists = new UserList(userList);
		
		mongoTemplate.insert(userLists);
		
		log.info("插入内嵌文档成功");
	}
	
	@Test
	public void queryList(){
		 Criteria criteria =new Criteria();
		 criteria.and("users.oid").is(1);
		
		Query query =new Query();
		query.addCriteria(criteria);
		
		List<UserList> userList = mongoTemplate.find(query, UserList.class);
		
		Criteria criteria2 =new Criteria();
		 criteria2.and("users.oid").is(2);
		
		Query query2 =new Query();
		query2.addCriteria(criteria2);
		
		List<UserList> userList2 = mongoTemplate.find(query2, UserList.class);
		
		System.out.println("1 - "+userList.size());
		
		System.out.println("2 - "+userList2.size());
		
		log.info("查询内嵌文档成功");
	}
	
	/**
	 * 修改数组现在使用的是先新增在删除
	 */
	@Test
	public void queryList2(){
		 Criteria criteria =new Criteria();
		 criteria.and("users").elemMatch(Criteria.where("oid").is(2));
		
		Query query =new Query();
		query.addCriteria(criteria);
		
		List<UserList> userList = mongoTemplate.find(query, UserList.class);
		
		System.out.println("1 - "+userList.size());
		
		User pullUser =new User(2,"2","2");
		
		User pushUser =new User(2,"3","3");
		
		Update update = new Update();
		//两个不能同时使用会报错
		update.push("users", pushUser);
//		update.pull("users", pullUser);
		
		mongoTemplate.updateMulti(query, update, UserList.class);
		
		log.info("查询内嵌文档成功");
	}
}
