package newb.c.dubbo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.RpcContext;

import newb.c.model.User;

/**
 * 但是，如后文所述，如果我们要用dubbo直接开发的消费端来访问此服务，则annotation必须放到接口上。
 * 如果接口和实现类都同时添加了annotation，则实现类的annotation配置会生效，接口上的annotation被直接忽略。
 * 、@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML}) 支持json和xml @Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
 * 、@Produces({"application/json", "text/xml"})  @Produces({"application/json; charset=UTF-8", "text/xml; charset=UTF-8"})
 * 如果所有方法都支持同样类型的输入输出数据格式，则我们无需在每个方法上做配置，只需要在服务类上添加annotation即可：
 * 也可使用这样
 * @author woshizbh
 *
 */
/*@Path("dubbox")
@Consumes({MediaType.APPLICATION_JSON})*/
public class RestServiceImpl implements RestService {
	
	@Override
	public String Hello() {
		return "Hello Dubbox";
	}
	
	/*@POST
    @Path("sayHello")
    @Consumes({MediaType.APPLICATION_JSON})*/
	/*public String sayHello(String name) {
		return "Hello1 REST" + name;
	}*/

	@Override
	/*@POST
    @Path("getUsers")
    @Consumes({MediaType.APPLICATION_JSON})*/
	public List getUsers() {
		List<User> list = new ArrayList<>();  
        User u1 = new User();  
        u1.setOid(50);  
        u1.setUsername("111");
        u1.setUsername("111");
          
        User u2 = new User();  
        u2.setOid(50);  
        u2.setUsername("222");
        u2.setUsername("222");  
          
        User u3 = new User();  
        u3.setOid(50);  
        u3.setUsername("333");
        u3.setUsername("333");  
          
        list.add(u1);  
        list.add(u2);  
        list.add(u3);  
        return list;
	}
	
	@Override
	/*@GET
	@Path("{id : \\d+}")
	@Produces({MediaType.APPLICATION_JSON})*/
	public User getUser(/*@PathParam("id")*/Long id) {
		User u = new User();  
        u.setOid(Integer.valueOf(id.toString()));  
        u.setUsername(id.toString());
        u.setPassword(id.toString());
        return u;
	}
	
	/*@GET
	@Path("{id : \\d+}")
	@Produces({MediaType.APPLICATION_JSON})*/
	/*public String getUser2(@PathParam("id") Long id) {
		System.out.println("Client address is " + RpcContext.getContext().getRemoteAddressString());
		return "Client address is " + RpcContext.getContext().getRemoteAddressString();
	}*/
	
	/*@GET
	@Path("{id : \\d+}")
	@Produces({MediaType.APPLICATION_JSON})*/
	/*public String getUser3(@PathParam("id") Long id, @Context HttpServletRequest request) {
		System.out.println("Client address is " + request.getRemoteAddr());
		return "Client address is " + request.getRemoteAddr();
	}*/
	
}
