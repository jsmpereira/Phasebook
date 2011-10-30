package jsmp.is.phasebook.ejb;

import java.text.DateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsmp.is.phasebook.db.Board;
import jsmp.is.phasebook.db.Topic;


public @Stateless class NotifierBean implements Notifier {

	@PersistenceContext(unitName="PhasebookJPA")
	private EntityManager em;
	@Resource(mappedName = "myMail")
	private Session mailSession;
	
	@Asynchronous
	public void boardNotification(int board_id, int topic_id) {
		
		Board board = em.find(Board.class, board_id);
		Topic topic = em.find(Topic.class, topic_id);
	    
	    try {
	        Message message = new MimeMessage(mailSession);
	        message.setFrom(new InternetAddress("no-reply@phasebook.com"));
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(board.getOwner().getEmail()));
	        message.setSubject("Phasebook - New board message");
	        DateFormat dateFormatter = DateFormat
	                .getDateTimeInstance(DateFormat.LONG,
	                DateFormat.SHORT);
	        Date timeStamp = new Date();

	        String messageText = "Hello, "+board.getOwner().getName()+"!\n" +
	        "<p>You have a new message on your " +
	         (board.isPrivate() ? "Private" : "Public") + " Board.</p>" +
	        "<h3>" + topic.getTitle() + "</h3>" +
	        "<blockquote>"+topic.getBody() + "</blockquote>" +
	        "<small> from " + topic.getCreator().getName() + " at "+topic.getCreated_at() + "</small>" +
	        "<p>View this message on Phasebook: http://localhost:8080/PhasebookWeb/Boards?id="+board.getId()+"</p>";
	         
	        message.setContent(messageText,"text/html; charset=UTF-8");
	        message.setHeader("X-Mailer", "PhaseBook Email");
	        message.setSentDate(timeStamp);

	        // Send message
	        Transport.send(message);
	    } catch (MessagingException ex) {
	        ex.printStackTrace();
	    }
	}
}
