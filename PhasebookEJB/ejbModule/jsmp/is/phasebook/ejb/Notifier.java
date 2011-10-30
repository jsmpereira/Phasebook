package jsmp.is.phasebook.ejb;

import javax.ejb.Remote;

@Remote
public interface Notifier {
	public void boardNotification(int board_id, int topic_id);
}
