/**
 * 
 */
package www.com.screenplay.awards.bo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import www.com.screenplay.awards.fileupload.ScreenPlayFileUpload;

/**
 * @author ssara  
 *   
 */
public class PayAndUploadBo {
	
	private String firstName;// ="Salih";
	private String lastName;//="Saran";
	private String eMail;//="ssaran1956@gmail.com";
	private String phone;//="2042556621";
	private String address;//="43 Trowbridge bay.";
	private String province_state;//="Manitoba";
	private String city;//="Winnipeg";
	private String postalCode;//="R2N 2V9";
	private String token;
	private String country;//="Canada";
	private Double payment= new Double(56.50);
	private Double reviewCharge = new Double(25.00);
	private Integer discount=0;
	private Integer resubmitDiscount=50;
	private String screenPlayTitle;//="Test Screen Play";
	private String logLine;
	//private ScreenPlayFileUpload fileUpload;
	private Double scriptReview;
	//private String resubmitDiscountText = "Resubmiting 50 % discount  ";
	//private String scriptReviewText = "Add script review for 25 US $  ";
	private List<SelectItem> genres;
	private String selectedGenre;
	private List<SelectItem> scriptAnalysis;
	private String selectedAnalysis;
	private byte[] fileBytes;
	private String fileName;
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	private String[] grns = {"Comedy","Drama","Thriller","Action","Sci-Fi","Horror","Fantasy","Musical","Animation","Short","TV Drama","TV Comedy"};

	public Double getScriptReview() {
		return scriptReview;
	}
	
/*public List<String> getGenres() {
		
		if(this.genres != null && this.genres.size() > 0)return this.genres;
		
		this.genres = new ArrayList<String>(0);
		//SelectItem selectItem = new SelectItem("Select Genre","Select Genre");
		//selectItem.setNoSelectionOption(true);
		genres.add("Select Genre");
		for(String grn:grns){
			//selectItem = new SelectItem(grn,grn);
			genres.add(grn);
		}
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}*/
 public void setGenres(List<SelectItem> genres) {
		this.genres = genres;
	}
 public List<SelectItem> getGenres() {
		
		if(this.genres != null && this.genres.size() > 0)return this.genres;
		
		this.genres = new ArrayList<SelectItem>(0);
		SelectItem selectItem = new SelectItem("Select Genre","Select Genre");
		selectItem.setNoSelectionOption(true);
		genres.add(selectItem);
		for(String grn:grns){
			selectItem = new SelectItem(grn,grn);
			genres.add(selectItem);
		}
		return genres;
	}

	
	public String getSelectedGenre() {
		return selectedGenre;
	}

	public void setSelectedGenre(String selectedGenre) {
		this.selectedGenre = selectedGenre;
	}

	public void setScriptReview(Double scriptReview) {
		this.scriptReview = scriptReview;
	}

	public String getResubmitDiscountText() {
		StringBuffer buf = new StringBuffer("Resubmiting ")
		.append(this.getResubmitDiscount())
		.append("% discount  ");
		return  buf.toString();
	}

//	public void setResubmitDiscountText(String resubmitDiscountText) {
//		this.resubmitDiscountText = resubmitDiscountText;
//	}

	public String getScriptReviewText() {
		StringBuffer buf = new StringBuffer("Add script review for ")
				.append(this.getScriptReview())
				.append(" US $  ");
		return  buf.toString();
	}

//	public void setScriptReviewText(String scriptReviewText) {
//		this.scriptReviewText = scriptReviewText;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastdName) {
		this.lastName = lastdName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince_state() {
		return province_state;
	}

	public void setProvince_state(String province_state) {
		this.province_state = province_state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getResubmitDiscount() {
		return resubmitDiscount;
	}

	public void setResubmitDiscount(Integer resubmitDiscount) {
		this.resubmitDiscount = resubmitDiscount;
	}

//	public ScreenPlayFileUpload getFileUpload() {
//		return fileUpload;
//	}
//
//	public void setFileUpload(ScreenPlayFileUpload fileUpload) {
//		this.fileUpload = fileUpload;
//	}

	public String getScreenPlayTitle() {
		return screenPlayTitle;
	}

	public void setScreenPlayTitle(String screenPlayTitle) {
		this.screenPlayTitle = screenPlayTitle;
	}

	public String getLogLine() {
		return logLine;
	}

	public void setLogLine(String logLine) {
		this.logLine = logLine;
	}

	public Double getReviewCharge() {
		return reviewCharge;
	}

	public void setReviewCharge(Double reviewCharge) {
		this.reviewCharge = reviewCharge;
	}
	
	public List<SelectItem> getScriptAnalysis() {
		if(this.scriptAnalysis != null && this.scriptAnalysis.size() > 0)return this.scriptAnalysis;
		
		this.scriptAnalysis = new ArrayList<SelectItem>(0);
		
		SelectItem si = new SelectItem();
		si.setValue("50.00");
		si.setLabel("Regular US $ 50.00");
		
		this.scriptAnalysis.add(si);
		
		si = new SelectItem();
		si.setValue("150.00");
		si.setLabel("Advanced US $ 150.00");
		
		this.scriptAnalysis.add(si);
		
		return scriptAnalysis;
	}

	public void setScriptAnalysis(List<SelectItem> scriptAnalysis) {
		this.scriptAnalysis = scriptAnalysis;
	}

	public String getSelectedAnalysis() {
		return selectedAnalysis;
	}

	public void setSelectedAnalysis(String selectedAnalysis) {
		this.selectedAnalysis = selectedAnalysis;
	}
	
}
