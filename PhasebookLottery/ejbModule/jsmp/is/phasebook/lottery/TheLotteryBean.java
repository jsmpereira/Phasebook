package jsmp.is.phasebook.lottery;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsmp.is.phasebook.db.Lottery;
import jsmp.is.phasebook.db.User;

public @Stateless class TheLotteryBean implements TheLottery {
	
	@PersistenceContext(unitName="PhasebookJPA")
	private EntityManager em;
	
	@EJB SimpleTimerBean timerBean;
	private int lucky_number;
	private Date next_run;
	private Date current_run;
	private int round;
	
	public TheLotteryBean() {
		this.setLucky_number(0);
	}
	
	public void setLucky_number(int lucky_number) {
		this.lucky_number = lucky_number;
	}

	public int getLucky_number() {
		this.lucky_number = timerBean.getNumber();
		this.current_run = timerBean.getCurrent_draw_at();
		this.next_run = timerBean.getNext_draw_at();
		this.round = timerBean.getCounter();
		return lucky_number;
	}

	public Date getNext_run() {
		return next_run;
	}

	public List<Lottery> getResults() {
		return em.createQuery("SELECT l from Lottery l WHERE number = :number_id AND round = :round")
		.setParameter("number_id", this.lucky_number).setParameter("round", this.round-1).getResultList();
	}

	public void purchase(int number, int user_id) {
		User user = (User) em.find(User.class, user_id);
		
		Lottery lottery = new Lottery(this.round, number, user);
		em.persist(lottery);
	}
	
	public int getRound() {
		return round;
	}
}
