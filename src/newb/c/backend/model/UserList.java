package newb.c.backend.model;

import java.util.List;

import newb.c.backend.model.basemodel.User;

public class UserList {
	private List<User> userList;

	public UserList() {
		super();
	}
	
	public UserList(List<User> userList) {
		super();
		this.userList = userList;
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
}
