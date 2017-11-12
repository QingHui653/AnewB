package newb.c.test;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import newb.c.a_spring.backend.sql.model.basemodel.User;

public class RestTemplateUnit {
	
	public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        List<User> user = restTemplate.getForObject("http://127.0.0.1:8080/AnewB/mvc/json", List.class);
        System.out.println(user.toString());
    }
}
