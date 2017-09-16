/**
 * 
 */
package www.com.screenplay.awards.backingbean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;

import www.com.screenplay.awards.backingbean.base.BackingBeanBase;
import www.com.screenplay.awards.bo.PayAndUploadBo;
import www.com.screenplay.awards.email.provider.EMailProviderType;
import www.com.screenplay.awards.email.sender.MessageSenderType;
import www.com.screenplay.awards.event.LaSpaScriptPayedAndUploaded;
import www.com.screenplay.awards.eventpublisher.LsSpaEventPublisher;
import www.com.screenplay.awards.fileupload.ScreenPlayFileUploadImpl;
import www.com.screenplay.awards.service.contract.PayAndUploadService;

/**
 * @author sarans
 *
 */    


public class LaSpaPayAndUloadBackingBeanImpl extends BackingBeanBase {

	private PayAndUploadService payAndUploadService;
	private LsSpaEventPublisher eventPublisher;
	
	public LsSpaEventPublisher getEventPublisher() {
		return eventPublisher;
	}

	public void setEventPublisher(LsSpaEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public PayAndUploadService getPayAndUploadService() {
		return payAndUploadService;
	}

	public void setPayAndUploadService(PayAndUploadService payAndUploadService) {
		this.payAndUploadService = payAndUploadService;
	}


	@Override
	public void onPreRender(ComponentSystemEvent event) {

		//System.out.println( "BAcking bean postback = "+ this.isPostback());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		
		String scriptType = (String)request.getParameter("scriptType");
		
		if(scriptType == null) return;
		
		this.getPayAndUploadService().getPayAndUploadBusinessObject().setPayment(this.getPayAndUploadService().getSubmissionPrice(scriptType));
		this.getPayAndUploadService().getPayAndUploadBusinessObject().setResubmitDiscount(50);
		

	}
	
	public void upload(){
		this.getPayAndUploadService().upload();
		this.getEventPublisher().publish(this.prepareEvent());
	}
	
	private LaSpaScriptPayedAndUploaded prepareEvent(){
		LaSpaScriptPayedAndUploaded event = new LaSpaScriptPayedAndUploaded(this.getPayAndUploadService().getPayAndUploadBusinessObject(),MessageSenderType.EMAIL_HTML_MIME,EMailProviderType.GMAIL);
		event.setDataToSubstitute(this.getPayAndUploadService().getDataToSubstitute());
		byte[] attachment = this.getPayAndUploadService().getPayAndUploadBusinessObject().getFileBytes();
		String screenPlayTitle = this.getPayAndUploadService().getPayAndUploadBusinessObject().getScreenPlayTitle();
		Map atachments = new HashMap(0);
		atachments.put(screenPlayTitle, attachment);
		event.setAttachments(atachments);
		return event;
	}
}
