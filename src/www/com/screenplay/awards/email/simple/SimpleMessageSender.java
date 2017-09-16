/**
 * 
 */
package www.com.screenplay.awards.email.simple;

import javax.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;

import www.com.screenplay.awards.email.sender.MessageSender;


/**
 * @author sarans
 *
 */
public class SimpleMessageSender extends MessageSender {

	
	public void sendMessage() throws MessagingException{
		
		SimpleMailMessage msg = new SimpleMailMessage();
		this.prepareMessage(msg);
		this.getSender().send(msg);
		
	}

	@Override
	protected void prepareMessage(Object message)
			throws MessagingException {
		
		((SimpleMailMessage)message).setTo(this.to);
		((SimpleMailMessage)message).setSubject(this.subject);
		((SimpleMailMessage)message).setFrom(this.from);
		((SimpleMailMessage)message).setText(this.text);
	}

}
