package jsmp.is.phasebook.ejb;

import javax.ejb.Remote;
import jsmp.is.phasebook.db.User;

@Remote
public interface Register {
	public boolean register(User user);
}

