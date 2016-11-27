package newb.c.model;

import java.io.Serializable;

public class SessionB implements Serializable {
	private User user;  //用户

	public SessionB(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
