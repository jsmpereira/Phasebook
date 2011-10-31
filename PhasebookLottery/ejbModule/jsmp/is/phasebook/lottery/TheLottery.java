package jsmp.is.phasebook.lottery;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import jsmp.is.phasebook.db.Lottery;

@Remote
public interface TheLottery {
	public int getLucky_number();
	public Date getNext_run();
	public int getRound();
	public List<Lottery> getResults();
	public void purchase(int number, int user_id);
}
