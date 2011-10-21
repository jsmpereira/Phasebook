package jsmp.is.phasebook.ejb;

import java.util.List;

import javax.ejb.Remote;

import jsmp.is.phasebook.db.Topic;
import jsmp.is.phasebook.db.User;

@Remote
public interface MessageBoard {
	public void createMessageBoard(User user, boolean isPrivate);
	public void createTopic(int board_id,String title, String body, int user_id);
	public List<Topic> getTopics(int board_id);
}
