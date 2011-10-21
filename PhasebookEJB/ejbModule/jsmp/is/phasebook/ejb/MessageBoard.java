package jsmp.is.phasebook.ejb;

import javax.ejb.Remote;

import jsmp.is.phasebook.db.User;

@Remote
public interface MessageBoard {
	public void createMessageBoard(User user, boolean isPrivate);
}
