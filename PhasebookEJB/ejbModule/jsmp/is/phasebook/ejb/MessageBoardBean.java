package jsmp.is.phasebook.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsmp.is.phasebook.db.Board;
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

}
