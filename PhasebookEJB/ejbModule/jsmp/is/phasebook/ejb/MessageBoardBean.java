package jsmp.is.phasebook.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsmp.is.phasebook.db.Board;
import jsmp.is.phasebook.db.Topic;
import jsmp.is.phasebook.db.User;
import jsmp.is.phasebook.ejb.MessageBoard;

public @Stateless class MessageBoardBean implements MessageBoard {

	@PersistenceContext(unitName="PhasebookJPA")
	private EntityManager em;
	
	public void createMessageBoard(User owner, boolean isPrivate) {
		
		Board board = new Board(isPrivate);
		board.setOwner(owner);
		em.persist(board);
	}

	public void createTopic(int board_id, String title, String body, int user_id) {
		Board board = em.find(Board.class, board_id);
		User creator = em.find(User.class, user_id);
		
		Topic topic = new Topic(title, body, creator, new Date());
		topic.setBoard(board);		
		em.persist(topic);
	}

	public List<Topic> getTopics(int board_id) {
		Board board = em.find(Board.class, board_id);
		return board.getTopics();
	}

}
