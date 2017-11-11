package newb.c.a_module.dubbo;

import java.util.List;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import newb.c.backend.sql.model.basemodel.User;
@Path("users")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface RestService {
	
	@GET
    @Path("Hello")
	String Hello();
	
	/*@POST
    @Path("{name:\\d+}")
	String sayHello(String name);*/
	
	@POST
    @Path("getUsers")
	List<User> getUsers();
	
	@GET
	@Path("{id : \\d+}")
	User getUser(@PathParam("id") Long id);
	
	/*@GET
	@Path("{id : \\d+}")
	String getUser2(Long id);
	
	@GET
	@Path("{id : \\d+}")
	String getUser3(Long id,HttpServletRequest request);*/
}
