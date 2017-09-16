/**
 * 
 */
package www.com.screenplay.awards.event;

import java.util.Date;
import java.util.List;
import java.util.Map;

import www.com.screenplay.awards.email.provider.EMailProviderType;
import www.com.screenplay.awards.email.sender.MessageSenderType;

/**
 * @author sarans
 *
 */
public interface LaSpaEvent {
	
	MessageSenderType getMessageSenderType();
	EMailProviderType getMailProviderType();
	String getMessage();
	String getSubject();
	Map<String,?> getAttachments();
	
	void   setAttachments(Map<String,?> attachments);
	void   setSubject(String subject);
	String addEmailEnd(String text);
	void setDataToSubstitute(String[] dataToSubstitute);
	String getSubmitterEmail();
	String getAdministratorMessage();
	
	
//	String getXmlLocation();
//	void setOrganization(String organization);
//	void setWarehouse(String warehouse);
//	void setOrderStatus(String orderStatus);
//	void setOrderedDate(String orderedDate);
//	void setOrderedquantity(String orderedquantity);
//	void setAnticipatedDeliveryDate(String anticipatedDeliveryDate);
//	void setNotes(String notes);
//	void setNotesHistory(Map<Date,String> notesMap);
//	String getNotes();
//	void setRequestedDate(String requestedDate);
//	void setRequestedQuantity(String requestedQuantity);
//	void setReceivedDate(String receivedDate);
//	void setReceivedQuantity(String receivedQuantity);
//	void setCompletedDate(String completedDate);
//	void setUserName(String userName);
//	void setItemOrConsumableDescription(String itemOrConsumableDescription);
//	void setPreviousRquestedQuantity(String previousRquestedQuantity);
//	void setPreviousOrderedQuantity(String previousOrderedQuantity);
//	void setPreviousAnticipatedDeliveryDate(String previousAnticipatedDeliveryDate);
//	void setPreviousReceivedQuantity(String previousReceivedQuantity);
//	void setPreviousReceivedDate(String previousReceivedDate);
//	void setOrganizationId(Long organizationId);
//	Long getOrganizationId();
	
	/*
 		dataToSubstitute[0]  = this.getPayAndUploadBusinessObject().getFirstName();//submitterName
		dataToSubstitute[1]  = this.getDedlineYear();//dedlineYear
		dataToSubstitute[2]  = this.getPayAndUploadBusinessObject().getScreenPlayTitle();//screnPlayTitle
		dataToSubstitute[3]  = this.scriptType;
		dataToSubstitute[4]  = this.getSubmissionPrice(this.scriptType)+"0";//getsubmitionFee  ?????????????? discount
		dataToSubstitute[5]  = "Not Orered";//(studioAnalysisType @ studioAnalysysFee) || (not ordered) ????????
		dataToSubstitute[6]  = this.getSubmissionPrice(this.scriptType)+"0";//(submitionFee+studioAnalysysFee)?????????????
		dataToSubstitute[7]  = this.getOrderNumberFromToken();//getFromToken("orderNumberFromToken");??????????????????????
		dataToSubstitute[8]  = (new Date()).toString();
		dataToSubstitute[9]  = this.getCreditCartInfoFromToken();//getFromToken("creditCardInfoFromToken");
		dataToSubstitute[10] = this.getPayAndUploadBusinessObject().getFirstName()+" "+this.getPayAndUploadBusinessObject().getLastName();//submittorFullName
		dataToSubstitute[11] = this.getPayAndUploadBusinessObject().getAddress();//submitterAddress
		dataToSubstitute[12] = this.getPayAndUploadBusinessObject().getCity();//submitterCity
		dataToSubstitute[13] = this.getPayAndUploadBusinessObject().getPostalCode();//submitterZip
		dataToSubstitute[14] = this.getPayAndUploadBusinessObject().getProvince_state();//submitterState
		dataToSubstitute[15] = this.getPayAndUploadBusinessObject().getCountry();//submitterCountry 
		dataToSubstitute[16] = this.getPayAndUploadBusinessObject().geteMail();//submitterEmail
		dataToSubstitute[17] = this.getPayAndUploadBusinessObject().getPhone();//submitterPhone
		dataToSubstitute[18] = "info@lascreenplayawards.com";
 	 
 */
	
	static final String eMailTemplate =  "<html><body><div align='left' style='font-family:arial;color:black;font-size:15px;'>"+
	"<p>Hello %1$s</p><br/>"+
	"<p>Thank you for your submission to the Los Angeles International Screenplay Awards!<br/>Your submittion has been successfully enterd for the %2$s deadline. </p>"+
	"<br/>"+
	"<p>Submitted:<br/>Entry Fee for %3$s - 1 %4$s Screenplay @ %5$s<br/>Studio Analysis - 1 %6$s</p>"+
	"<br/>"+
	"<p>Total Charges: %7$s<br/>All amounts are in US. Dollars</p>"+
	"<br/>"+		
	"<p>Order Number: %8$s<br/>Date of order: %9$s<br/>Credit card<br/>%10$s</p>"+
	"<br/>"+
	"<p>Name: 		%11$s<br/>Address: 	%12$s<br/>City:			%13$s<br/>Zip:			%14$s<br/>State:		%15$s<br/>Country:	%16$s<br/>Email:		%17$s<br/>Phone:		%18$s</p>"+
	"</div>"+
	"<br/><br/>"+
	"<hr></hr>"+
	"<p align='left' style='font-family:arial;color:black;font-size:12px;'>If you have any questions regarding this order, please contact us: %19$s</p>"+
	"<br/>"+
	"<p>Thank you for your submittion  & good luck!<br/>LAISA Team</p>"+
	"</div></body></html>";
	
	static final String eMailAdministratorMessageTemplate = "<html><body><div align='left' style='font-family:arial;color:black;font-size:15px;'>"+
	"<p>Applicant %s has applied for Los Angeles International Screenplay Awards in %s competition year!<br/>Attached is submited script.</p>"+
	"</div></body></html>";	
	
	static final String eMailEnd =  "<br></br><br></br><br></br><br></br><hr></hr><p align='left' style='font-family:arial;color:black;font-size:10px;'>Do not reply to this email. The sender information is not a valid email address." +
			"This email and the information transmitted therein contain privileged and/or confidential material and may be subject to copyright." +
			"It is intended only for the use of the individual or entity to which it is addressed." +
			"Any unauthorized review, copying, disclosure, retransmission, dissemination or other use of or taking of any action in reliance upon, this information by persons or entities other than the intended recipient is strictly prohibited." +
			"If you have received this e-mail in error, please delete all copies, including attached material, from any computer.</p>";
	
}
