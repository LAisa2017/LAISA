/**
 * 
 */
package www.com.screenplay.awards.email.mime;

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
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import www.com.screenplay.awards.email.sender.MessageSender;





/**
 * @author sarans
 *
 */
public class HtmlMimeMessageSender extends MessageSender {
	
private final String contentType = "text/html; charset=ISO-8859-1";

	@Override
public void sendMessage() throws MessagingException{
		
		MimeMessage msg = this.getSender().createMimeMessage();
		this.prepareMessage(msg);
		this.getSender().send(msg);
	}

	@Override
	protected void prepareMessage(Object message)
			throws MessagingException {
		
		LaSpaMimeMessagePreparator preparator = new LaSpaMimeMessagePreparator();
		try {
			preparator.prepare((MimeMessage)message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public String getContentType() {
		return contentType;
	}

	private class LaSpaMimeMessagePreparator implements MimeMessagePreparator{
		
		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setFrom(from);
			InputStreamSource instr =   new ByteArrayResource(text.getBytes());
			messageHelper.addInline("LaSpa", instr,getContentType());
			if(attachments == null)return;
			Set<String> keys = attachments.keySet();
			for(String key:keys){
				byte[] attach = (byte[]) attachments.get(key);
				messageHelper.addAttachment(key,new ByteArrayDataSource(attach,"application/pdf"));
				
			}
		}
		
	}
}
