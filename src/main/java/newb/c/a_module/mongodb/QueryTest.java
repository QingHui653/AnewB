package newb.c.a_module.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

import newb.c.backend.model.UserList;
import newb.c.backend.model.basemodel.Movie;
import newb.c.backend.model.basemodel.User;

public class QueryTest {

	private String url = "192.168.1.188";
	private int port = 27017;
	private String database = "platform";
	
	private MongoTemplate mongoTemplate;
	private Mongo mongo;

	@Before
	public void connDataBase() {
		
		mongo =new Mongo(url, port);
		mongoTemplate =new MongoTemplate(mongo ,database);
	}
	
	@After
	public void close(){
		mongo.close();
	}

	@Test
	public void insertList() {
		try {
			UserList userList =new UserList();
			List<User> users =new ArrayList<>();
				User u1 =new User();
				u1.setOid(1);
				u1.setUsername("1");
				u1.setPassword("1");
				users.add(u1);
				
				User u2 =new User();
				u2.setOid(2);
				u2.setUsername("2");
				u2.setPassword("2");
				users.add(u2);
			
			userList.setUserList(users);
			mongoTemplate.insert(userList);
			
			System.out.println("mongoTemplate文档插入成功");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	@Test
	public void queryList() {
		try {
			Query query = new Query();
	        Criteria criteria =new Criteria();
	        criteria.and("userList.oid").is(1);
	        List<UserList> userList1 = mongoTemplate.find(query, UserList.class);
	        System.out.println("1 -"+userList1.size());
	        
	        Query query2 = new Query();
	        Criteria criteria2 =new Criteria();
	        criteria2.and("userList.oid").is(2);
	        List<UserList> userList2 = mongoTemplate.find(query2, UserList.class);
	        System.out.println("2 -"+userList2.size());
	        
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
