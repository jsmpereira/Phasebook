package jsmp.is.phasebook.lottery;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

public @Stateless class TheLotteryBean implements TheLottery {
	
	@EJB SimpleTimerBean timerBean;
	private int lucky_number;
	private Date next_run;
	
	public TheLotteryBean() {
		this.setLucky_number(0);
	}
	
	public void setLucky_number(int lucky_number) {
		this.lucky_number = lucky_number;
	}

	public int getLucky_number() {
		this.lucky_number = timerBean.getNumber();
		return lucky_number;
	}

	public void setNext_run(Date next_run) {
		this.next_run = next_run;
	}

	public Date getNext_run() {
		this.next_run = timerBean.getNext_draw_at();
		return next_run;
	}

}
