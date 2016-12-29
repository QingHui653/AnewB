package newb.c.backend.model;

import java.io.Serializable;
import newb.c.backend.model.basemodel.User;

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
