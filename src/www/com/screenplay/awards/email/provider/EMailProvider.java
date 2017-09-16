/**
 * 
 */
package www.com.screenplay.awards.email.provider;

import java.util.Properties;

import www.com.screenplay.awards.email.sender.MessageSender;
import www.com.screenplay.awards.email.sender.MessageSenderType;

/**
 * @author sarans
 *
 */
public interface EMailProvider {
	//MessageSender createMessageSender(String host) throws Exception;
	MessageSender createMessageSender(MessageSenderType messageSenderType,String userName, String password, EMailProviderType emProviderType) throws Exception;
}
