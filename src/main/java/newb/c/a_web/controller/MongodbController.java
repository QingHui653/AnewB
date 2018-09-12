package newb.c.a_web.controller;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import newb.c.a_spring.backend.sql.model.UserTrin;
import newb.c.a_spring.backend.sql.model.basemodel.Nove;
import newb.c.a_spring.backend.sql.model.basemodel.Title;
import newb.c.a_spring.backend.sql.service.NoveService;
import newb.c.a_spring.backend.sql.service.TitleService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import newb.c.a_spring.backend.sql.model.basemodel.Movie;
import newb.c.a_spring.backend.sql.model.basemodel.User;

/**
 * @ClassName LoginController
 * @version 1.0
 */
@Controller
@RequestMapping("resources/mongodb")
public class MongodbController {
	
	@Autowired(required=false)
	private MongoTemplate mongoTemplate;
    @Autowired
	private NoveService noveService;
	@Autowired
	private TitleService titleService;

    @GetMapping("switch")
    public void switchDb(){
        DBCollection dbCollection;

        /*dbCollection = mongoTemplate.getDb().getCollection("nove");
        DBCursor noves = dbCollection.find();
        while (noves.hasNext()){
            DBObject doc=noves.next();
            String tid =(String)doc.get("_id");
            String name =(String)doc.get("name");
            String noveType =(String)doc.get("noveType");
            String titleType =(String)doc.get("titleType");

            Nove nove = new Nove(tid,name,noveType,titleType);
            noveService.save(nove);
        }*/


        dbCollection = mongoTemplate.getDb().getCollection("title");
        DBCursor dbObjects = dbCollection.find();
        int i=0;
        while(dbObjects.hasNext()){
            DBObject doc=dbObjects.next();
            String tid =(String)doc.get("tid");
            Integer index =(Integer)doc.get("index");
            String content =(String)doc.get("content");

            Title title = new Title(i,tid,index,content);
            titleService.save(title);
            i++;
        }
    }
	/**
     * 插入用户信息
     */
	@RequestMapping(value="testAddUser",method=RequestMethod.GET)
	@ApiOperation(value="测试mongdb插入数据")
    public void testAddUser() {
	    List<User> userList =new ArrayList<>();
	    User user =null;
        for (int i = 0; i < 10; i++) {
            user=new User();
            user.setOid(i);
            user.setPassword(String.valueOf(i));
            user.setUsername(String.valueOf(i));
            userList.add(user);
        }
        mongoTemplate.insertAll(userList);
    }

    /**
     * 插入用户信息
     */
    @RequestMapping(value="testAddUserTrin",method=RequestMethod.GET)
    @ApiOperation(value="测试mongdb插入 内嵌 数据 手动方式")
    public void testAddUserTrin() {
        List<UserTrin> userTrinList =new ArrayList<>();
        User user =null;
        UserTrin userTrin=null;
        for (int i = 0; i < 10; i++) {
            user=new User();
            user.setOid(i);
            user.setPassword(String.valueOf(i));
            user.setUsername(String.valueOf(i));

            userTrin=new UserTrin();
            userTrin.setId(i);
            userTrin.setName(String.valueOf(i));
            userTrin.setAge(i);
            userTrin.setUser(user);

            userTrinList.add(userTrin);
        }
        mongoTemplate.insertAll(userTrinList);
    }

    @RequestMapping(value="testQueryUserTrin",method=RequestMethod.GET)
    @ApiOperation(value="查询 内嵌 数据 手动方式")
    public void testQueryUserTrin() {
        Query query = new Query();
        Criteria criteria =Criteria.where("id").lt(10).andOperator(Criteria.where("user.oid").gte(5));
        query.addCriteria(criteria);

        List<UserTrin> userTrinList = mongoTemplate.find(query,UserTrin.class);

        userTrinList.forEach(System.out::println);
    }

    @RequestMapping(value="testUpdateUserTrin",method=RequestMethod.GET)
    @ApiOperation(value="更新 内嵌 数据 手动方式")
    public void testUpdateUserTrin() {
        Query query = new Query();
        Criteria criteria =Criteria.where("id").lt(10).andOperator(Criteria.where("user.oid").gte(5));
        query.addCriteria(criteria);

        Update update = new Update();
//        update.rename("更新 内嵌文档 ","user.username");
        update.set("user.username","更新 内嵌文档 ");

        mongoTemplate.updateMulti(query,update,UserTrin.class);

        List<UserTrin> userTrinList = mongoTemplate.find(query,UserTrin.class);

        userTrinList.forEach(System.out::println);
    }

    /**
     * 插入用户信息
     */
    @RequestMapping(value="testAddUserTrin2",method=RequestMethod.GET)
    @ApiOperation(value="测试mongdb插入 内嵌 数据 引用方式")
    public void testAddUserTrin2() {
        List<UserTrin> userTrinList =new ArrayList<>();
        UserTrin userTrin=null;
        Movie movie =null;
        for (int i = 10; i < 20; i++) {
            movie=new Movie();
            movie.setId(i);

            userTrin=new UserTrin();
            userTrin.setId(i);
            userTrin.setName(String.valueOf(i));
            userTrin.setAge(i);
            userTrin.setMovie(movie);

            userTrinList.add(userTrin);
        }
        mongoTemplate.insertAll(userTrinList);
    }

    @RequestMapping(value="testQueryUserTrin2",method=RequestMethod.GET)
    @ApiOperation(value="查询 内嵌 数据 引用方式")
    public void testQueryUserTrin2() {
        Query query = new Query();

        Criteria criteria =Criteria.where("id").gte(10);
        query.addCriteria(criteria);

        List<UserTrin> userTrinList = mongoTemplate.find(query,UserTrin.class);

        for (UserTrin userTrin : userTrinList) {
            Movie movie = mongoTemplate.findOne(new Query(Criteria.where("id").is(userTrin.getMovie().getId())), Movie.class);
            userTrin.setMovie(movie);
        }

        userTrinList.forEach(System.out::println);
    }

    /**
     * 测试删除数据
     */
    @GetMapping("testRemoveUser")
    @ApiOperation(value="测试mongodb删除数据")
    public void testRemoveUser() {
        Query query = new Query();
//        Criteria criteria = new Criteria();
//        criteria.where("oid").gt(22);
        // 条件删除
        query.addCriteria(Criteria.where("oid").gt(5));
        mongoTemplate.remove(query, User.class);
    }

    /**
     * 更新用户数据
     */
    @GetMapping("testUpdateUser")
    @ApiOperation(value="测试mongodb更新数据")
    public void testUpdateUser() {
        // update(query,update,class)
        // Query query:需要更新哪些用户,查询参数
        // Update update:操作符,需要对数据做什么更新
        // Class class:实体类

        // 更新age大于24的用户信息
        Query query = new Query();
        query.addCriteria(Criteria.where("oid").gt(3));


        Update update = new Update();
        // 值加2
        update.inc("oid", 2);
        // update.set("name", "zhangsan"); 直接赋值
        // update.unset("name"); 删去字段
        // update.push("interest", "java"); 把java追加到interest里面,interest一定得是数组
        // update.pushAll("interest", new String[]{".net","mq"})
        // 用法同push,只是pushAll一定可以追加多个值到一个数组字段内
        // update.pull("interest", "study"); 作用和push相反,从interest字段中删除一个等于value的值
        // update.pullAll("interest", new String[]{"sing","dota"})作用和pushAll相反
        // update.addToSet("interest", "study") 把一个值添加到数组字段中,而且只有当这个值不在数组内的时候才增加
        // update.rename("oldName", "newName") 字段重命名

        // 只更新第一条记录,age加122,name值更新为zhangsan
        mongoTemplate.updateFirst(query, new Update().inc("oid", 2).set("username", "更新第一条"), User.class);

        // 批量更新,更新所有查询到的数据
        mongoTemplate.updateMulti(query, update, User.class);

    }

    /**
     * 查询用户信息
     */
    @GetMapping("testQueryUser")
    @ApiOperation(value="测试mongodb查询数据")
    public void testQueryUser() {
        // 查询主要用到Query和Criteria两个对象
        Query query = new Query();
        Criteria criteria =Criteria.where("oid").gt(3);
        criteria.and("username").is("更新第一条");
//         criteria.and("name").is("cuichongfei");等于
        // List<String> interests = new ArrayList<String>();
        // interests.add("study");
        // interests.add("linux");
        // criteria.and("interest").in(interests); in查询
        // criteria.and("home.address").is("henan"); 内嵌文档查询
        // criteria.and("").exists(false); 列存在
        // criteria.and("").lte(); 小于等于
        // criteria.and("").regex(""); 正则表达式
        // criteria.and("").ne(""); 不等于
        // 多条件查询
        // criteria.orOperator(Criteria.where("key1").is("0"),Criteria.where("key1").is(null));

        query.addCriteria(criteria);
        List<User> userList1 = mongoTemplate.find(query, User.class);
        printList(userList1);

        // 排序查询sort方法,按照age降序排列
        //query.sort().on("age", Order.DESCENDING);
        // List<User> userList2 = mongoTemplate.find(query, User.class);
        // printList(userList2);

        // 指定字段查询,只查询age和name两个字段
        // query.fields().include("age").include("name");
        // List<User> userList3 = mongoTemplate.find(query, User.class);
        // printList(userList3);

        // 分页查询
        // query.skip(2).limit(3);
        // List<User> userList4 = mongoTemplate.find(query, User.class);
        // printList(userList4);

        // 查询所有
        // printList(mongoTemplate.findAll(User.class));

        // 统计数据量
        // System.out.println(mongoTemplate.count(query, User.class));

    }

    @RequestMapping(value="testAddMovie",method=RequestMethod.GET)
    @ApiOperation(value="测试mongdb插入 Movie数据")
    public void testAddMovie() {
        List<Movie> movieList =new ArrayList<>();
        Movie movie =null;
        for (int i = 0; i < 20; i++) {
            movie=new Movie();
            movie.setId(i);
            movie.setMovieLink("第一条Link"+i);
            movie.setMovieName("第一个Name"+i);
            movieList.add(movie);
        }
        mongoTemplate.insertAll(movieList);
    }

    @GetMapping("testMovieName")
    @ResponseBody
    @ApiOperation(value="测试mongodb查询电影名")
    public Object testMovieName(String name ) {
        // 查询主要用到Query和Criteria两个对象
        Query query = new Query();
        Criteria criteria =new Criteria();

        criteria.and("movieName").regex(".*"+name+".*");

        query.addCriteria(criteria);

        List<Movie> movieList = mongoTemplate.find(query, Movie.class);

        return movieList;
    }


    public void printList(List<User> userList) {
        for (User user : userList) {
            System.out.println(user);
        }
    }
    
    /**
     * 数组文档指定元素更新
     */
    /*public void testUpdateDocument() {
        // 只更新一个内嵌文档
//        Update update = new Update();
//        update.set("sonModelList.$.state", 2);
//        mongoTemplate.updateMulti(new Query(Criteria.where("sonModelList.geoNum").is("532568248122605568")), update, CatchThirteenthModel.class);
        
        // 批量更新所有的内嵌文档
//        update.set("sonModelList.$.state", 2);
//        mongoTemplate.updateMulti(new Query(Criteria.where("sessionId").is("cf23e870-2c5a-4d1f-9652-6fa1793dc8be")), 
//                new Update().set("state", 2), CatchThirteenthModel.class);
//        mongoTemplate.updateMulti(new Query(Criteria.where("sonModelList.parentGeoNum").is("532568523000512512")), 
//                new Update().set("state", 2), CatchFifteenthModel.class);
        List<CatchFifteenthModel> find = mongoTemplate.find(new Query(Criteria.where("sonModelList.parentGeoNum").is("532568523000512512")), CatchFifteenthModel.class);
        System.out.println("");
    }*/
    
    /**
     * 测试findAndModify方法
     */
    /*public void testFindAndModify() {
        
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("geoNum").is("526547322448904192"), Criteria.where("geoLevel").is(14));
        CatchModel model = mongoTemplate.findAndModify(new Query(criteria), new Update().set("state", 2).set("endTime", new Date()), new FindAndModifyOptions().returnNew(true), CatchModel.class);
        
        System.out.println(model);
    }*/
	
}
