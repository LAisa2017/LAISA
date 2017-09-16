/**
 * 
 */
package www.com.screenplay.awards.event;

import www.com.screenplay.awards.email.provider.EMailProviderType;
import www.com.screenplay.awards.email.sender.MessageSenderType;

/**
 * @author sarans
 *
 */
public class LaSpaScriptPayedAndUploaded extends LaSpaEmailEventImpl {
	public LaSpaScriptPayedAndUploaded(Object source, MessageSenderType messageSenderType,EMailProviderType mailProviderType) {
		super(source, messageSenderType,mailProviderType);
		this.eventDescription = "Script Etered Competition-Payed and Uploaded.";
	}
}
