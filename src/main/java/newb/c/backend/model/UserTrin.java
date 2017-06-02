package newb.c.backend.model;

import newb.c.backend.model.basemodel.User;

public class UserTrin{
	private Integer id;

    private String name;

    private Integer age;
    
    private User user;
    
	public UserTrin() {
		super();
	}

	public UserTrin(Integer id, String name, Integer age, User user) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.user = user;
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
}
