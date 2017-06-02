package newb.c.dubbo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import newb.c.backend.model.basemodel.User;  
public class RestTest {
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("Larry");

		Client client = ClientBuilder.newClient();//127.0.0.1:2181
		WebTarget target = client.target("http://localhost:8081/AnewB/users/Hello");
		Response response = target.request().get();
//		System.out.println("The generated id is " + response.toString());
//		Response response = target.request().post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
		System.out.println(" "+response.readEntity(String.class));
		try {
		    if (response.getStatus() != 200) {
		        throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
		    }
		    System.out.println("The generated id is " + response.readEntity(User.class).getUsername());
		} finally {
		    response.close();
		    client.close(); // 在真正开发中不要每次关闭client，比如HTTP长连接是由client持有的
		}
	}
}
