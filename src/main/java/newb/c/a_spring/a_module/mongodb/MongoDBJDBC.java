/**
 * @author woshizbh
 *
 */
package newb.c.a_spring.a_module.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Sorts.orderBy;

public class MongoDBJDBC{
   
   private String url ="119.23.231.239";
   private int port =27017;
   private String database ="upload";
   private String user ="qh";
   private String password ="726214";
   
   @SuppressWarnings({ "resource", "unused" })
//   @Test
   public void creatClientNoPW(){
	   try{   
	       // 连接到 mongodb 服务
	       MongoClient mongoClient = new MongoClient(url , port );
	       
	         // 连接到数据库
	       MongoDatabase mongoDatabase = mongoClient.getDatabase(database);  
	       System.out.println("Connect to database successfully");
	       
	      }catch(Exception e){
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	     }
   }
   
   @SuppressWarnings({ "resource", "unused" })
   @Test
   public void creatClientWithPW(){
	   try {  
           //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
           //ServerAddress()两个参数分别为 服务器地址 和 端口  
           ServerAddress serverAddress = new ServerAddress(url,port);  
           List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
           addrs.add(serverAddress);  
             
           //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
           MongoCredential credential = MongoCredential.createScramSha1Credential(user, database, password.toCharArray());  
           List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
           credentials.add(credential);  
             
           //通过连接认证获取MongoDB连接  
           MongoClient mongoClient = new MongoClient(addrs,credentials);  
             
           //连接到数据库  
           MongoDatabase mongoDatabase = mongoClient.getDatabase(database);


           FindIterable<Document> findIterable = mongoDatabase.getCollection("title").find(Filters.eq("tid", "893321")).sort(orderBy(Sorts.ascending("index")));

           MongoCursor<Document> mongoCursor = findIterable.iterator();
           while(mongoCursor.hasNext()){
               System.out.println(mongoCursor.next());
           }

           System.out.println("Connect to database successfully");
       } catch (Exception e) {  
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );  
       }  
   }
   
   
}
   
