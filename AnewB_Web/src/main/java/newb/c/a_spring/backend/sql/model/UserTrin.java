package newb.c.a_spring.backend.sql.model;

import lombok.Data;
import newb.c.a_spring.backend.sql.model.basemodel.Movie;
import newb.c.a_spring.backend.sql.model.basemodel.Result;
import newb.c.a_spring.backend.sql.model.basemodel.User;

@Data
public class UserTrin{
	private Integer id;

    private String name;

    private Integer age;
    
    private User user;

	private Result result;

	private Movie movie;
    
	public UserTrin() {
		super();
	}

	public UserTrin(Integer id, String name, Integer age, User user, Result result) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.user = user;
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
