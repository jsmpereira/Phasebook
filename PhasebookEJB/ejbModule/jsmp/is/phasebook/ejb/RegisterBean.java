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
	@EJB MessageBoard messageboardBean;
	
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
			messageboardBean.createMessageBoard(user, false);
			messageboardBean.createMessageBoard(user, true);
			return true;
		}
		return false;
	}
}
