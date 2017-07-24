package newb.c.backend.model;

import java.util.List;

import newb.c.backend.model.basemodel.User;

public class UserList {
	private List<User> users;

	public UserList() {
		super();
	}
	
	public UserList(List<User> userList) {
		super();
		this.users = userList;
	}
	
	public List<User> getUserList() {
		return users;
	}

	public void setUserList(List<User> userList) {
		this.users = userList;
	}
	
}
