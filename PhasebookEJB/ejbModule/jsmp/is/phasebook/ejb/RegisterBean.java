package jsmp.is.phasebook.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsmp.is.phasebook.db.User;

public @Stateless class RegisterBean implements Register {

	@PersistenceContext(unitName="PhasebookJPA")
	private EntityManager em;
	@EJB MessageBoard messageboard;
	
	public boolean register(User user) {
		List<User> results = em.createQuery("SELECT email FROM User u WHERE u.email = :user_email")
		.setParameter("user_email", user.getEmail()).getResultList();
		
		if(!results.isEmpty()) {
			// user exists
		} else {
			//register new user			
			// write user to db
			em.persist(user);
			
			// create public and private board
			messageboard.createMessageBoard(user, false);
			messageboard.createMessageBoard(user, true);
			return true;
		}
		return false;
	}
	
	/*public static void main(String args[]) {
		RegisterBean reg = new RegisterBean();
		AuthenticationBean auth = new AuthenticationBean();
		
		User user = new User("manuel Pedro", "jsmp@student.dei.uc.pt", "bingo");
		//reg.register(user);
		
		System.out.println(".... login in...");
		
		auth.logout(user);
		
		
	}*/

}
