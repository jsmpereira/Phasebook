package jsmp.is.phasebook.lottery;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface TheLottery {
	public int getLucky_number();
	public Date getNext_run();
}
