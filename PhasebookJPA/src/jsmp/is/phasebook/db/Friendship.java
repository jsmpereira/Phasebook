package jsmp.is.phasebook.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Friendship
 *
 */
@Entity
@Table(name="friendships")
public class Friendship implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName="user_id")
	private User user;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "friend_id", referencedColumnName="friend_id")
	private User friend;
	private Date accepted_at;

	public Friendship() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public User getFriend() {
		return friend;
	}

	public void setAccepted_at(Date accepted_at) {
		this.accepted_at = accepted_at;
	}

	public Date getAccepted_at() {
		return accepted_at;
	}
   
}
