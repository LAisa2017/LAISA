/**
 * 
 */
package www.com.screenplay.awards.email.sender;

import java.util.Properties;

/**
 * @author sarans
 *
 */
public enum MessageSenderType {
	

	EMAIL_SIMPLE("www.com.screenplay.awards.email.simple.SimpleMessageSender"),
	EMAIL_ATTACHMENT("www.com.screenplay.awards.email.attachment.AttachmentMessageSender"),
	EMAIL_HTML_MIME("www.com.screenplay.awards.email.mime.HtmlMimeMessageSender");
	
	private String messageSenderClassName;
	private MessageSenderType(String messageSenderClassName){
		this.messageSenderClassName = messageSenderClassName;
	}
	public MessageSender getMessageSender(String username,String password, Properties props ){
		MessageSender ms = null;
		try {
			@SuppressWarnings("rawtypes")
			Class msClass = Class.forName(this.messageSenderClassName);
			ms = (MessageSender) msClass.newInstance();
			ms.setParams(username, password, props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ms;
	}
}
