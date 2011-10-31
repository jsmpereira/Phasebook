package jsmp.is.phasebook.lottery;

import java.util.Date;
import java.util.Random;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;

@Singleton
public class SimpleTimerBean {

	private int number = 0;
	private Date current_draw_at;
	private Date next_draw_at;
	private int counter = 0;

	@Schedule(minute = "*/1", hour="*", persistent=false)
	public void run(Timer timer) {
		this.number = new Random().nextInt(100)+1;
		this.current_draw_at = new Date();
		this.next_draw_at = timer.getNextTimeout();
		System.out.println("round: "+counter+  " --- NUMBER -----> " + this.number);
		
		this.counter++;
	}
	
	public int getNumber() {
		return number;
	}

	public Date getNext_draw_at() {
		return next_draw_at;
	}

	public Date getCurrent_draw_at() {
		return current_draw_at;
	}

	public int getCounter() {
		return counter;
	}
}
