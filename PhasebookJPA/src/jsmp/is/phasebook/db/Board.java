package jsmp.is.phasebook.db;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="boards")
public class Board {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany(mappedBy="board", fetch = FetchType.EAGER)
	@OrderBy("created_at ASC")
	private Set<Topic> topics;
	@ManyToOne
	private User owner;
	private boolean isPrivate;
	
	public Board() {}
	
	public Board(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}
}
