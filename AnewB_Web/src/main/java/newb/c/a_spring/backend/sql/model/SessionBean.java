package newb.c.a_spring.backend.sql.model;

import java.io.Serializable;
import newb.c.a_spring.backend.sql.model.basemodel.User;

public class SessionBean implements Serializable {
	private User user;  //用户

	public SessionBean(User user) {
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
