package jsmp.is.phasebook.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Lottery
 *
 */
@Entity
@Table(name="lotteries")
public class Lottery implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int number;
	private int round;
	private Date created_at;
	@ManyToOne
	private User user;
	
	public Lottery() {
		super();
	}

	public Lottery(int round, int number, User user) {
		this.round = round;
		this.number = number;
		this.user = user;
		this.created_at = new Date();
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getRound() {
		return round;
	}
}
