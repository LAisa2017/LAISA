/**
 * 
 */
package www.com.screenplay.awards.eventhandler;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationEvent;

import www.com.screenplay.awards.email.provider.EMailProviderType;
import www.com.screenplay.awards.email.sender.MessageSender;
import www.com.screenplay.awards.email.sender.MessageSenderType;
import www.com.screenplay.awards.event.LaSpaEvent;
import www.com.screenplay.awards.event.LaSpaEmailEventImpl;

/**
 * @author sarans
 *
 */
public class LaSpaEmailHandler implements LaSpaEventHandler {
	
//	private String mailHost;
	private String from = "TEST_notification@wclc.com";
	private String subject = "TEST_notification from Gmail";
	private Map<String,?> attachments; // list of the paths to attached files
//	private EventReceiverCollection eventReceivers;
//	private BusyFlag busyFlag = BusyFlag.createBusyFlag();
//	private Boolean debugMode = false;
	
	private EMailProviderType mailProviderType;
	
	
	private String emailUserId;
	private String emailPassword;
	private String[] administrators;
	private String admins; 
	
	public String getAdmins() {
		return admins;
	}

	public void setAdmins(String admins) {
		this.admins = admins;
	}

	public void initialize(){
		this.administrators = this.admins.split(",");
	}
	
//	private String mailSmtpAuth;
//	private String mailSmtpStarttlEnable;
//	private String mailSmtpPort;
	
//	public String getMailSmtpAuth() {
//		return mailSmtpAuth;
//	}
//
//	public void setMailSmtpAuth(String mailSmtpAuth) {
//		this.mailSmtpAuth = mailSmtpAuth;
//	}
//
//	public String getMailSmtpStarttlEnable() {
//		return mailSmtpStarttlEnable;
//	}
//
//	public void setMailSmtpStarttlEnable(String mailSmtpStarttlEnable) {
//		this.mailSmtpStarttlEnable = mailSmtpStarttlEnable;
//	}
//
//	public String getMailSmtpPort() {
//		return mailSmtpPort;
//	}
//
//	public void setMailSmtpPort(String mailSmtpPort) {
//		this.mailSmtpPort = mailSmtpPort;
//	}

	
	
	public String getEmailUserId() {
		return emailUserId;
	}

	public void setEmailUserId(String emailUserId) {
		this.emailUserId = emailUserId;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	
//	public LaSpaEmailHandler(){
//		
//	}
//	
//	public LaSpaEmailHandler(String eMailHost){
//		this.mailHost = eMailHost;
//	}
//	public LaSpaEmailHandler(String eMailHost,List<String> attachments){
//		this.mailHost = eMailHost;
//		this.attachments = attachments;
//	}
//	
//	public String getMailHost() {
//		return mailHost;
//	}
//
//	public void setMailHost(String mailHost) {
//		this.mailHost = mailHost;
//	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Map<String,?> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Map<String,?> attachments) {
		this.attachments = attachments;
	}

	

	/* (non-Javadoc)
	 * @see www.com.screenplay.awards.eventhandler.LaSpaEventHandler#handleEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void handleEvent(ApplicationEvent event) {
		
//		System.err.println("Handling Email Event  " + event.getClass().getCanonicalName());
		
		LaSpaEmailEventImpl laSpaEmailEvent = (LaSpaEmailEventImpl) event;
		MessageSenderType senderType = laSpaEmailEvent.getMessageSenderType();
		EMailProviderType mailProviderType = laSpaEmailEvent.getMailProviderType();
		
		Properties mailSenderProperties = mailProviderType.getProperties();
		MessageSender ms = senderType.getMessageSender(this.emailUserId, this.emailPassword, mailSenderProperties);
		
		for(String admin:this.administrators){
			this.subject = "Script Submited";
			this.attachments = laSpaEmailEvent.getAttachments();
			this.notifyReceiver(ms, admin, laSpaEmailEvent.getAdministratorMessage(), true);
		}
		this.attachments = null;
		this.subject = "Script Submition confirmation and payment receit";
		this.notifyReceiver(ms, laSpaEmailEvent.getSubmitterEmail(), laSpaEmailEvent.getMessage(), false);
		
	
//		this.subject = ((WclcEvent)event).getSubject();
//		this.attachments = ((WclcEvent)event).getAttachments();
//		if(this.getDebugMode())System.out.println("Handling Event => "+event.getClass().getName()+"  => "+(new Date()).toString());
//		if(event instanceof WclcTISecurityCertificateEventImpl){
//			this.handleTISecurityEvent(event);
//		}else{
//			this.handleWclcEvent(event);
//		}

	}
	
//	private void handleTISecurityEvent(ApplicationEvent event){
//		busyFlag.getBusyFlag();
//		if(this.getDebugMode())System.out.println("Method handleTISecurityEvent  => "+(new Date()).toString());
//		try {
//			WclcEvent e = (WclcEvent)event;
//			MessageSenderType msType = e.getMessageSenderType();
//			MessageSender ms = msType.getMessageSender(this.mailHost);
//			List<EventReceiver> receivers = ((WclcTISecurityCertificateEventImpl)e).getReceivers();
//			for(EventReceiver receiver:receivers){
//				this.notifyReceiver(ms, receiver.getDistributionGroup(), e.addEmailEnd(receiver.getMessage()),receiver.getSendAttachments());
//			}	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			busyFlag.freeBusyFlag();
//		}
//	}
	
	private void notifyReceiver(MessageSender ms,String receiver,String message,Boolean sendAttachment){
		
		ms.setTo(receiver);
		ms.setFrom(this.from);
		ms.setText(message);
		ms.setSubject(this.subject);
		try {
			if(sendAttachment != null && sendAttachment.booleanValue()){
				ms.setAttachments(this.attachments);
			}else{
				ms.setAttachments(null);
			}
			ms.sendMessage();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		if(this.getDebugMode())System.out.println("Notify Receiver => "+receiver+"  => "+(new Date()).toString());
//		ms.setTo(receiver);
//		ms.setFrom(this.from);
//		ms.setText(message);
//		ms.setSubject(this.subject);
//		try {
//			if(this.attachments != null && !this.attachments.isEmpty()){
//				if(sendAttachment != null && sendAttachment.booleanValue()){
//					if(this.getDebugMode()){
//						System.out.println("Sending Attachments:  => "+(new Date()).toString());
//						for(String attachment:this.attachments){
//							System.out.println(attachment);
//						}
//					}
//					ms.setAttachments(this.attachments);
//				}else{
//					if(this.getDebugMode())System.out.println("Sending email NO ATTACHMENTS. => "+(new Date()).toString());
//					ms.setAttachments(null);
//				}
//			}else{
//				if(this.getDebugMode())System.out.println("Sending email NO ATTACHMENTS. => "+(new Date()).toString());
//				if(ms.getAttachments() != null && ms.getAttachments().isEmpty() && this.getDebugMode()){
//					System.err.println("Message Sender has NON empty attachments - Attachments should be null object at this point.");
//					System.err.println("ms.setAttachments(null) must be invoced.");
//					for(String attachment:this.attachments){
//						System.err.println("Attachment : "+attachment);
//					}
//				}
//			}
//			ms.sendMessage();
//			
//		} catch (MessagingException e) {
//			// do nothing
//		}
	}

	public EMailProviderType getMailProviderType() {
		return mailProviderType;
	}

	public void setMailProviderType(EMailProviderType mailProviderType) {
		this.mailProviderType = mailProviderType;
	}

	
}
