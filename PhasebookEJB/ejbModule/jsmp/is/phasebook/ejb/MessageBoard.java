package jsmp.is.phasebook.ejb;

import java.util.List;

import javax.ejb.Remote;

import jsmp.is.phasebook.db.Asset;
import jsmp.is.phasebook.db.Board;
import jsmp.is.phasebook.db.User;

@Remote
public interface MessageBoard {
	public void createMessageBoard(User user, boolean isPrivate);
	public void createTopic(int board_id,String title, String body, String filepath, int user_id);
	public Board getBoard(int board_id);
	public List<Asset> getAssets();
}
