/**
 * 
 */
package www.com.screenplay.awards.email.sender;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;





/**
 * @author sarans
 *
 */
public abstract class MessageSender {
	
	protected String to;
	protected String from;
	protected String subject;
	protected JavaMailSender sender = new JavaMailSenderImpl();
	protected String text; 
	protected Map<String,?> attachments;

	public 		abstract void sendMessage() 						throws MessagingException;
	protected 	abstract void prepareMessage(Object message) 	throws MessagingException;
	
	public static MessageSender getInstance(MessageSenderType senderType,String username,String password,Properties props ){
		return senderType.getMessageSender(username, password, props);
	}
	
	
	/**
	 * @return the attachments
	 */
	public Map<String,?> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(Map<String,?> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * @return the sender
	 */
	public JavaMailSender getSender() {
		return sender;
	}
	
	/**
	 * @param sender the sender to set
	 */
	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}
	
	public MessageSender setParams(String username,String password,Properties props ){
		
		((JavaMailSenderImpl)this.sender).setUsername(username);
		((JavaMailSenderImpl)this.sender).setPassword(password);
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

		((JavaMailSenderImpl)this.sender).setSession(session);
		
		return this;
	}
	
}
