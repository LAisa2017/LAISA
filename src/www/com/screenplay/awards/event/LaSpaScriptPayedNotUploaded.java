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
public class LaSpaScriptPayedNotUploaded extends LaSpaEmailEventImpl {
	public LaSpaScriptPayedNotUploaded(Object source, MessageSenderType messageSenderType,EMailProviderType mailProviderType) {
		super(source, messageSenderType,mailProviderType);
	}
}
