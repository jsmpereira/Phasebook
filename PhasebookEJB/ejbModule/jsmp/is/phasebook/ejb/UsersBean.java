package jsmp.is.phasebook.ejb;

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
	public boolean friendsOrPending(int user_id, int friend_id) {
		// TODO Auto-generated method stub
		
		List<Friendship> friendships = em.createQuery("SELECT f FROM Friendship f WHERE user_id = :user_id AND friend_id = :friend_id")
		.setParameter("user_id", user_id).setParameter("friend_id", friend_id).getResultList();
		
		if (!friendships.isEmpty()) {
			return true;
		}
		
		return false;
	}
}
