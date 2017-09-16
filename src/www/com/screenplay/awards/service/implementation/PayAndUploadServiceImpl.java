/**
 * 
 */
package www.com.screenplay.awards.service.implementation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.json.JSONStringer;

import com.google.gson.GsonBuilder;


import www.com.screenplay.awards.bo.PayAndUploadBo;
import www.com.screenplay.awards.fileupload.ScreenPlayFileUploadImpl;
import www.com.screenplay.awards.service.contract.PayAndUploadService;

/**
 * @author sarans
 *
 */
public class PayAndUploadServiceImpl implements PayAndUploadService {
	
	private PayAndUploadBo payAndUploadBusinessObject;
	private ScreenPlayFileUploadImpl screanPlayFileUpload;
	
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private Date dl1 = null;
	private Date dl2 = null;
	private Date dl3 = null;
	private String currentYear;
	private String scriptType;
	
	public void initialize(){
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		this.currentYear = ""+calendar.get(Calendar.YEAR);
		try {
			this.dl1 = df.parse(currentYear+"-08-01");
			this.dl2 = df.parse(currentYear+"-09-01");
			this.dl3 = df.parse(currentYear+"-10-31");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PayAndUploadBo getPayAndUploadBusinessObject() {
		return payAndUploadBusinessObject;
	}
	public void setPayAndUploadBusinessObject(PayAndUploadBo payAndUploadBusinessObject) {
		this.payAndUploadBusinessObject = payAndUploadBusinessObject;
	}
	public ScreenPlayFileUploadImpl getScreanPlayFileUpload() {
		return screanPlayFileUpload;
	}
	public void setScreanPlayFileUpload(ScreenPlayFileUploadImpl screanPlayFileUpload) {
		this.screanPlayFileUpload = screanPlayFileUpload;
	}
	
	public void upload(){
		this.screanPlayFileUpload.upload();
		Map<String,byte[]> files = this.screanPlayFileUpload.getFilesBytes();
		Entry<String,byte[]> firstEntry = files.entrySet().iterator().next();
		String fileName = firstEntry.getKey();
		byte[] fileBytes = firstEntry.getValue();
		payAndUploadBusinessObject.setFileName(fileName);
		payAndUploadBusinessObject.setFileBytes(fileBytes);
	}
	
	public Double getSubmissionPrice(String scriptType){
		//this.initialize();
		if(scriptType != null){
			this.scriptType = scriptType;
		}
		Double price = null;
		
		Calendar calendar = Calendar.getInstance();
		Calendar calendar_dl1 = Calendar.getInstance();
		Calendar calendar_dl2 = Calendar.getInstance();
		Calendar calendar_dl3 = Calendar.getInstance();
		
//		calendar.setTime(this.now);
		calendar_dl1.setTime(this.dl1);
		calendar_dl2.setTime(this.dl2);
		calendar_dl3.setTime(this.dl3);
		
		if(scriptType.toLowerCase().equals("tv")){
			if(calendar.before(calendar_dl1)){
				return new Double(49.00);
			}else if(calendar.before(calendar_dl2)){
				return new Double(54.00);
			}else if(calendar.before(calendar_dl3)){
				return new Double(59.00);
			}
		}else if(scriptType.toLowerCase().equals("short")){
			if(calendar.before(calendar_dl1)){
				return new Double(39.00);
			}else if(calendar.before(calendar_dl2)){
				return new Double(44.00);
			}else if(calendar.before(calendar_dl3)){
				return new Double(49.00);
			}
		}else if(scriptType.toLowerCase().equals("feature")){
			if(calendar.before(calendar_dl1)){
				return new Double(59.00);
			}else if(calendar.before(calendar_dl2)){
				return new Double(64.00);
			}else if(calendar.before(calendar_dl3)){
				return new Double(69.00);
			}
		}
		return price;
	}
	
	private String getDedlineYear(){
		//this.initialize();
		String ret = "";
		Calendar calendar_dl3 = Calendar.getInstance();
		Calendar calendar_now = Calendar.getInstance();
		calendar_now.setTime(new Date());
		calendar_dl3.setTime(this.dl3);
		if(calendar_now.before(calendar_dl3)){
			ret = ret + this.currentYear.toString();
		}else{
			ret = ret + (this.currentYear+1).toString();
		}
		return ret;
	}
	
	private String getCreditCartInfoFromToken(){
		String ret = "";
		JSONObject jsonToken = new JSONObject(this.payAndUploadBusinessObject.getToken());
		JSONObject card = jsonToken.getJSONObject("card");
		String brand = card.getString("brand");
		ret = brand+" # XXXX XXXX XXXX "+card.getString("last4");
		return ret;
	}
	private String getOrderNumberFromToken(){
		String ret = "";
		JSONObject jsonToken = new JSONObject(this.payAndUploadBusinessObject.getToken());
		ret =jsonToken.getString("id");
		return ret;
	}
	
	@Override
	public String[] getDataToSubstitute() {
		String[] dataToSubstitute = new String[19];
		
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
		return dataToSubstitute;
	}
	
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
}
