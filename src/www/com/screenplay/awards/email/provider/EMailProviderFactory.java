/**
 * 
 */
package www.com.screenplay.awards.email.provider;

import www.com.screenplay.awards.email.sender.MessageSender;
import www.com.screenplay.awards.email.sender.MessageSenderType;

/**
 * @author sarans
 *
 */
public class EMailProviderFactory implements EMailProvider{
	
	private MessageSenderType senderType;
	
	public EMailProviderFactory(){
		
	}
	
	public EMailProviderFactory(String sendertype){
		setMessageSenderType(sendertype);
	}

	public EMailProviderFactory(MessageSenderType senderType){
		this.senderType = senderType;
	}

	public MessageSenderType getMessageSenderType() {
		return senderType;
	}

	public void setMessageSenderType(String messageSenderType) {
		this.senderType = MessageSenderType.valueOf(messageSenderType);
	}

	@Override
	public MessageSender createMessageSender(MessageSenderType messageSenderType,String userName, String password, EMailProviderType emProviderType) throws Exception {
		// TODO Auto-generated method stub
		if(this.senderType == null){
			throw new Exception("Ilegal State - Sender type is null.Or invalid type is specified");
		}else{
			return this.senderType.getMessageSender(userName, password, emProviderType.getProperties());
		}
		
	}

//	@Override
//	public MessageSender createMessageSender (
//			MessageSenderType messageSenderType,String userName, String password, Properties props)  throws Exception{
//		this.senderType = messageSenderType;
//		return this.createMessageSender(host);
//	}

}
