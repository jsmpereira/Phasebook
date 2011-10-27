package jsmp.is.phasebook.ejb;

import javax.ejb.Remote;

import jsmp.is.phasebook.db.User;

@Remote
public interface Authentication {
	public User login(String email, String password);
	public boolean logout(int user_id);
}
