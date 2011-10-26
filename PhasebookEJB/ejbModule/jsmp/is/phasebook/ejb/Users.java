package jsmp.is.phasebook.ejb;

import java.util.List;

import javax.ejb.Remote;

import jsmp.is.phasebook.db.Friendship;
import jsmp.is.phasebook.db.User;

@Remote
public interface Users {
	public List<User> getUsers();
	public void requestFriendShip(int user_id, int friend_id);
	public List<Friendship> getFriends(int user_id);
	public void acceptFriendship(int user_id, int friend_id);
	public boolean isFriendsWith(int user_id, int friend_id);
	public boolean isPendingFriendsWith(int user_id, int friend_id, boolean bidireccional);
	public List<User> findUsers(String query);
}
