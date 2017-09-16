/**
 * 
 */
package www.com.screenplay.awards.event;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationEvent;

import www.com.screenplay.awards.email.provider.EMailProviderType;
import www.com.screenplay.awards.email.sender.MessageSenderType;

/**
 * @author sarans
 *
 */
@SuppressWarnings("serial")
public abstract class LaSpaEmailEventImpl extends ApplicationEvent implements LaSpaEvent {
	
	private MessageSenderType messageSenderType;
	private EMailProviderType mailProviderType;
	
	//protected String message;
	protected String subject;
	protected Map<String,?> attachments;
	protected String eventDescription;
	
	protected String[] dataToSubstitute;
	
/*
 	dataToSubstitute[0]  = submitterName
 	dataToSubstitute[1]  = dedlineYear
 	dataToSubstitute[2]  = screnPlayTitle
 	dataToSubstitute[3]  = scriptType
 	dataToSubstitute[4]  = submitionFee
 	dataToSubstitute[5]  = (studioAnalysisType @ studioAnalysysFee) || (not ordered)
 	dataToSubstitute[6]  = (submitionFee+studioAnalysysFee)
 	dataToSubstitute[7]  = orderNumberFromToken
 	dataToSubstitute[8]  = today
 	dataToSubstitute[9]  = creditCardInfoFromToken
 	dataToSubstitute[10] = submitterName
 	dataToSubstitute[11] = submitterAddress
 	dataToSubstitute[12] = submitterCity
 	dataToSubstitute[13] = submitterZip
 	dataToSubstitute[14] = submitterState
 	dataToSubstitute[15] = submitterCountry
 	dataToSubstitute[16] = submitterEmail
 	dataToSubstitute[17] = submitterPhone
 	 
 */
	
//	public static final Map<String,String> mikhaelsAddress;
//	static{
//		mikhaelsAddress = new HashMap<String,String>();
//		mikhaelsAddress.put("Name:", "Mikhael Bassilli");
//		mikhaelsAddress.put("Address:", "110244 Balboa Blvd., unit 211");
//		mikhaelsAddress.put("City:", "Los Angeles");
//		mikhaelsAddress.put("Zip:", "91344");
//		mikhaelsAddress.put("State:", "CA");
//		mikhaelsAddress.put("Country:", "US");
//		mikhaelsAddress.put("Email:", "mike@playthinkentertainment.com");
//		mikhaelsAddress.put("Phone:", "");
//	}
	
	/*
	 * Attachmets could be
	 * 1. Map<String,String> key = file Name, value = full path to file
	 * 2. Map<String,byte[]> key = file Name, value = in memory file bytes
	 */
	
	
	
	public LaSpaEmailEventImpl(Object source,MessageSenderType messageSenderType,EMailProviderType mailProviderType) {
		super(source);
		this.messageSenderType = messageSenderType;
		this.mailProviderType  = mailProviderType;
	}

	public MessageSenderType getMessageSenderType() {
		return messageSenderType;
	}

	public EMailProviderType getMailProviderType() {
		return mailProviderType;
	}

	public String addEmailEnd(String text){
		StringBuffer ret = new StringBuffer();
		ret.append(text);//.append(LaSpaEvent.eMailEnd);
		return ret.toString();
	}
	
	public Map<String,?> getAttachments() {
		return this.attachments;
	}
	
	public void setAttachments(Map<String,?> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String getMessage() {
		return String.format(LaSpaEvent.eMailTemplate,this.dataToSubstitute);
	}

	public void setDataToSubstitute(String[] dataToSubstitute) {
		this.dataToSubstitute = dataToSubstitute;
	}
	
	public String getSubmitterEmail(){
		return this.dataToSubstitute[16];
	}

	@Override
	public String getSubject() {
		this.createSubjectLine();
		return this.subject;
	}

	@Override
	public void setSubject(String subject) {
		// TODO Auto-generated method stub
		
	}
	
	protected void createSubjectLine(){
		if(this.subject != null && !this.subject.isEmpty())return;
		StringBuffer sb  = new StringBuffer(this.eventDescription);
		sb.append('\u0020');
//		sb.append(this.itemOrConsumableDescription);
//		sb.append('\u0020');
//		sb.append('\u002d');
//		sb.append('\u0020');
//		sb.append(this.eventDescription);
		this.subject =  sb.toString();
	}
	
	public String getAdministratorMessage(){
		StringBuffer ret = new StringBuffer(String.format(LaSpaEvent.eMailAdministratorMessageTemplate,this.dataToSubstitute[10],this.dataToSubstitute[1]));
		ret.append(LaSpaEvent.eMailEnd);
		return ret.toString();
	}
}
