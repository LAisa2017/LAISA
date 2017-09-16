/**
 * 
 */
package www.com.screenplay.awards.email.attachment;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessageHelper;

import www.com.screenplay.awards.email.sender.MessageSender;


/**
 * @author sarans
 *
 */
public class AttachmentMessageSender extends MessageSender {
	
	public void sendMessage() throws MessagingException{
		
		MimeMessage msg = this.getSender().createMimeMessage();
		this.prepareMessage(msg);
		this.getSender().send(msg);
		
	}

	@Override
	protected void prepareMessage(Object message)
			throws MessagingException {

		MimeMessageHelper helper = new MimeMessageHelper((MimeMessage)message,true);
		helper.setTo(this.to);
		helper.setFrom(this.from);
		helper.setSubject(this.subject);
		helper.setText(this.text);
		Set<String> keys = this.attachments.keySet();
		for(String key:keys){
			byte[] attach = (byte[]) attachments.get(key);
			helper.addAttachment(key,new ByteArrayDataSource(attach,"application/pdf"));
		}
		
	}
	


}
