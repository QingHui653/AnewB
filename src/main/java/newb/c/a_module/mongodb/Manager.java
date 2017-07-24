package newb.c.a_module.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import newb.c.backend.model.basemodel.User;

public class Manager {

	private String url = "192.168.1.188";
	private int port = 27017;
	private String database = "platform";

	private MongoDatabase mongoDatabase;
	private MongoClient mongoClient;
	private MongoCollection<Document> collection;
	
	private MongoTemplate mongoTemplate;

	@Before
	public void connDataBase() {
		// 连接到 mongodb 服务
		mongoClient = new MongoClient(url, port);
		// 连接到数据库
		mongoDatabase = mongoClient.getDatabase(database);
		System.out.println("        @Before 连接数据库成功");
		// 获取集合 ，没有则创建
		collection = mongoDatabase.getCollection("test");
		System.out.println("        @Before 集合 test 选择成功");
		
		Mongo mongo =new Mongo(url, port);
		mongoTemplate =new MongoTemplate(mongo ,database);
	}
	
	@After
	public void close(){
		if(mongoClient!=null)
			mongoClient.close();
		System.out.println("        @After 连接关闭");
	}

	// @Test
	public void creatCollection() {
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient(url, port);

			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
			System.out.println("Connect to database successfully");

			MongoCollection<Document> collection = mongoDatabase.getCollection("test");
			System.out.println("集合 test 选择成功");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@Test
	public void insertDoc() {
		try {
			// 插入文档
			/**
			 * 1. 创建文档 org.bson.Document 参数为key-value的格式 2. 创建文档集合List
			 * <Document> 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List
			 * <Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
			 */
			Document document = new Document("title", "MongoDB").append("description", "database").append("likes", 100)
					.append("by", "Fly");
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertMany(documents);
			
			User user =new User();
			user.setOid(2);
			user.setUsername("1111");
			user.setPassword("xxxx");
			mongoTemplate.insert(user);
			
			System.out.println("文档插入成功");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

//	@Test
	public void delDoc() {

		// 删除符合条件的第一个文档
		collection.deleteOne(Filters.eq("likes", 200));
		// 删除所有符合条件的文档
		collection.deleteMany(Filters.eq("likes", 200));
		System.out.println("文档删除成功");
		// 检索查看结果
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(mongoCursor.next());
		}
	}

	// @Test
	public void findDoc() {
		// 检索所有文档
		/**
		 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
		 * 通过游标遍历检索出的文档集合
		 */
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println("  mongodb--:" + mongoCursor.next());
		}

	}

//	@Test
	public void updateDoc() {
		// 更新文档 将文档中likes=100的文档修改为likes=200
		collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
		System.out.println("文档更新成功");
		// 检索查看结果
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println("  mongodb--:" + mongoCursor.next());
		}
	}
}
