package jsmp.is.phasebook.ejb;

import java.util.List;

import javax.ejb.Remote;

import jsmp.is.phasebook.db.User;

@Remote
public interface Users {
	public List<User> getUsers();
	public void requestFriendShip(int user_id, int friend_id);
	public boolean friendsOrPending(int user_id, int friend_id);
}
