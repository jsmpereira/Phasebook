package jsmp.is.phasebook.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsmp.is.phasebook.db.Friendship;
import jsmp.is.phasebook.db.User;
import jsmp.is.phasebook.ejb.Users;

public @Stateless class UsersBean implements Users {

	@PersistenceContext(unitName="PhasebookJPA")
	private EntityManager em;
	
	public List<User> getUsers() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}

	public void requestFriendShip(int user_id, int friend_id) {
		
		User user = em.find(User.class, user_id);
		User friend = em.find(User.class, friend_id);
				
		Friendship friendship = new Friendship();
		friendship.setUser(user);
		friendship.setFriend(friend);
		
		em.persist(friendship);
	}

	@Override
	public List<Friendship> getFriends(int user_id) {
		//return em.find(User.class, user_id).getFriends();
		return em.createQuery("SELECT f from Friendship f where user_id = :user_id AND accepted_at IS NOT NULL").setParameter("user_id", user_id).getResultList();
	}
	
	

	@Override
	public void acceptFriendship(int user_id, int friend_id) {
		List<Friendship> friendships = em.createQuery("SELECT f FROM Friendship f WHERE user_id = :user_id AND friend_id = :friend_id").setParameter("user_id", user_id).setParameter("friend_id", friend_id).getResultList();
		
		friendships.get(0).setAccepted_at(new Date());
	}

	@Override
	public List<User> findUsers(String query) {
		return em.createQuery("SELECT u FROM User u WHERE name LIKE :name").setParameter("name", "%"+query+"%").getResultList();
	}

	@Override
	public boolean isFriendsWith(int user_id, int friend_id) {
		
		List<Friendship> friends = em.createQuery("SELECT f FROM Friendship f WHERE accepted_at IS NOT NULL AND (user_id = :user_id AND friend_id = :friend_id) OR (user_id = :friend_id AND friend_id = :user_id)").setParameter("user_id", user_id).setParameter("friend_id", friend_id).getResultList();
		
		if (!friends.isEmpty())
			return true;
		return false;
	}
	
	@Override
	public boolean isPendingFriendsWith(int user_id, int friend_id, boolean bidireccional) {
		
		List<Friendship> friends = null;
		
		if (bidireccional)
			friends = em.createQuery("SELECT f FROM Friendship f WHERE accepted_at IS NULL AND ((user_id = :user_id AND friend_id = :friend_id) OR (user_id = :friend_id AND friend_id = :user_id))").setParameter("user_id", user_id).setParameter("friend_id", friend_id).getResultList();
		else
			friends = em.createQuery("SELECT f FROM Friendship f WHERE accepted_at IS NULL AND user_id = :user_id AND friend_id = :friend_id").setParameter("user_id", user_id).setParameter("friend_id", friend_id).getResultList();
		
		if (!friends.isEmpty())
			return true;
		return false;
	}
}
