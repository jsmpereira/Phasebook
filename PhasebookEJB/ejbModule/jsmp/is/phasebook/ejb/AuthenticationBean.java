package jsmp.is.phasebook.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsmp.is.phasebook.db.User;

public @Stateless class AuthenticationBean implements Authentication {

	@PersistenceContext(unitName="PhasebookJPA")
	private EntityManager em;
	
	public int login(String email, String password) {
		
		List<User> results = em.createQuery("SELECT u FROM User u WHERE u.email = :user_email AND u.password = :user_password")
		.setParameter("user_email", email).setParameter("user_password", password).getResultList();
		
		if(!results.isEmpty()) {
			// user exists, we can login
			User user_to_be_logged = results.get(0);
			
			user_to_be_logged.setLoggedIn(true);
			em.persist(user_to_be_logged);
			return user_to_be_logged.getId();
		} else {
			// user not found			
		}
		return -1;
		
	}

	@Override
	public boolean logout(int user_id) {
		
		List<User> results = em.createQuery("SELECT u FROM User u WHERE u.id = :user_id")
		.setParameter("user_id", user_id).getResultList();
		
		if(!results.isEmpty()) {
			// user exists, we can login
			User user_to_be_logged = results.get(0);
			
			user_to_be_logged.setLoggedIn(false);
			em.persist(user_to_be_logged);
			return true;
		} else {
			// user not found			
		}
		return false;
	}

}
