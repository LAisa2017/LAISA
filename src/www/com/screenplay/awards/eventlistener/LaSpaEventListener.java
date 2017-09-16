/**
 * 
 */
package www.com.screenplay.awards.eventlistener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

import www.com.screenplay.awards.event.LaSpaEmailEventImpl;
import www.com.screenplay.awards.event.LaSpaScriptPayedAndUploaded;
import www.com.screenplay.awards.eventhandler.LaSpaEventHandler;

/**
 * @author sarans
 *
 */
public class LaSpaEventListener  implements ApplicationListener<LaSpaEmailEventImpl> {

	private String mailHost;// = "hqmail.hq.wclc.com";
	private LaSpaEventHandler eventHandler;
	
	
	@Override
	public void onApplicationEvent(LaSpaEmailEventImpl laSpaEmailEvent) {
		
		this.getEventHandler().handleEvent(laSpaEmailEvent);
		/*if(!(appEvent instanceof WclcEvent))return;
		MessageSenderType msType = ((WclcEvent)appEvent).getMessageSenderType();
		if(this.eventHandler == null){
			if(msType.equals(MessageSenderType.EMAIL_SIMPLE) || msType.equals(MessageSenderType.EMAIL_ATTACHMENT))
			{
				this.eventHandler = new WclcEmailHandler(this.mailHost);
			}
		}
		
		//if(msType.equals(MessageSenderType.EMAIL_SIMPLE)){
		//	this.eventHandler.handleEvent(appEvent);
		//}else if(msType.equals(MessageSenderType.EMAIL_ATTACHMENT)){
			// create attachment and send email
			//List<String> attachments = ((WclcEvent)appEvent).getAttachments();
			//((WclcEmailHandler)this.eventHandler).setAttachments(attachments);
			this.eventHandler.handleEvent(appEvent);
		//}*/
	}


	public String getMailHost() {
		return mailHost;
	}


	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}


	public LaSpaEventHandler getEventHandler() {
		return eventHandler;
	}


	public void setEventHandler(LaSpaEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}
}
