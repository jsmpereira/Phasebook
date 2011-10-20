package jsmp.is.phasebook.ejb;

import javax.ejb.Remote;

@Remote
public interface Authentication {
	public int login(String email, String password);
	public boolean logout(int user_id);
}
